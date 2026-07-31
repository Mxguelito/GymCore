package database.migration;

import java.util.ArrayList;
import java.util.List;

import java.io.File;

import java.util.Comparator;

public class MigrationRunner {
	
	private List<Migration> migrations;
	
	private SchemaVersionDAO schemaVersionDAO;
	
	private ScriptExecutor scriptExecutor;

    public void run() {

        System.out.println();

        System.out.println("=======================================");
        System.out.println("     GYMCORE MIGRATION RUNNER");
        System.out.println("=======================================");

        cargarMigraciones();
        
        inicializarSchemaVersion();
        
        
        
        ejecutarGrupo(
                "database/migrations",
                "V",
                "MIGRACIONES"
        );

        ejecutarGrupo(
                "database/seeds",
                "S",
                "SEEDS"
        );

        System.out.println();

    }
    
    private void ejecutarGrupo(String carpeta, String prefijo, String titulo) {

        System.out.println();
        System.out.println("========== " + titulo + " ==========");

        buscarArchivos(carpeta, prefijo);

        verificarMigraciones();

        mostrarMigraciones();

        ejecutarPendientes(carpeta);

    }

    private void cargarMigraciones() {

        System.out.println("• Cargando migraciones...");

    }
    
    
    
    private void mostrarMigraciones() {

        System.out.println();

        for (Migration migration : migrations) {

            System.out.println(
                    migration.getVersion()
                    + " - "
                    + migration.getNombre()
                    + " -> "
                    + (migration.isEjecutada() ? "OK" : "PENDIENTE")
            );

        }

    }
    
    private void buscarArchivos(String carpetaPath, String prefijo) {

        migrations.clear();

        File carpeta = new File(carpetaPath);

        File[] archivos = carpeta.listFiles();

        if (archivos == null) {

            System.out.println("No se encontraron scripts.");

            return;

        }

        System.out.println();

        System.out.println("Scripts encontrados en: " + carpetaPath);

        for (File archivo : archivos) {

            if (!archivo.getName().startsWith(prefijo)) {
                continue;
            }

            Migration migration = crearMigration(archivo);

            migrations.add(migration);

        }

        migrations.sort(
                Comparator.comparing(Migration::getVersion)
        );

    }
    
    private Migration crearMigration(File archivo) {

        String nombreArchivo = archivo.getName();

        String sinExtension =
                nombreArchivo.replace(".sql", "");

        String[] partes = sinExtension.split("_", 2);

        String version = partes[0];

        String nombre = partes[1];

        return new Migration(
                version,
                nombre,
                "",
                nombreArchivo,
                false
        );

    }

    private void verificarMigraciones() {

        System.out.println("• Verificando versiones...");

        List<String> versionesEjecutadas =
                schemaVersionDAO.obtenerVersionesEjecutadas();

        for (Migration migration : migrations) {

            if (versionesEjecutadas.contains(migration.getVersion())) {

                migration.setEjecutada(true);

            } else {

                migration.setEjecutada(false);

            }

        }

    }

    private void ejecutarPendientes(String carpetaPath) {

        boolean hayPendientes = false;

        for (Migration migration : migrations) {

            if (!migration.isEjecutada()) {

                hayPendientes = true;
                break;

            }

        }

        if (!hayPendientes) {

            System.out.println();
            System.out.println("✔ No hay scripts pendientes en " + carpetaPath);

            return;

        }

        System.out.println();
        System.out.println("Ejecutando scripts de: " + carpetaPath);

        for (Migration migration : migrations) {

            if (!migration.isEjecutada()) {

                System.out.println();
                System.out.println("Ejecutando: " + migration.getArchivo());

                boolean ejecutada =
                        scriptExecutor.ejecutar(
                                carpetaPath + "/"
                                + migration.getArchivo());

                if (ejecutada) {

                    schemaVersionDAO.registrar(migration);

                    migration.setEjecutada(true);

                }

            }

        }

    }
    
    public MigrationRunner() {

        migrations = new ArrayList<>();

        schemaVersionDAO = new SchemaVersionDAO();
        
        scriptExecutor = new ScriptExecutor();

    }
    
    private void inicializarSchemaVersion() {

        if (schemaVersionDAO.existeTabla()) {

            return;

        }

        System.out.println();

        System.out.println("Creando tabla schema_version...");

        boolean creada = scriptExecutor.ejecutar(
                "database/migrations/create_schema_version.sql"
        );

        if (creada) {

            System.out.println("✔ Tabla schema_version creada.");

        } else {

            System.out.println("❌ No se pudo crear schema_version.");

        }

    }

}