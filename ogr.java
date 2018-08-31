private static Integer runCommand(List<String> params) {
        message = "";
        returnValue = 0;

        try {
            var runner = new ProcessBuilder(params);
            var env = runner.environment();
            env.put("SHAPE_ENCODING", "ISO-8859-1");
            var p = runner.start();
            p.waitFor();

            // read the input and error streams...some applications send stdout into stderr...
            try(var combined = new SequenceInputStream(p.getInputStream(), p.getErrorStream());
                var inputReader = new BufferedReader(new InputStreamReader(combined))) {
                message += inputReader.lines().collect(Collectors.joining(" "));
            }
            returnValue = p.exitValue();
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    private static void runOgr(String shapeFile) {
        var tableName = "";
        var dsn = String.format(
                "PG:host=%s dbname=%s user=%s password=%s",
                cfg.getHost(),
                cfg.getDatabase(),
                cfg.getUser(),
                cfg.getPassword()
        );

        var params = Arrays.asList(
                cfg.getOgrPath(),
                "-f", "PostgreSQL", dsn,
                "--config", "PG_USE_COPY", "YES",
                "-append",
                "-nln", String.format("%s.%s", cfg.getSchema(), tableName),
                "-lco", "GEOM_TYPE=geometry",
                "-lco", "GEOMETRY_NAME=geom",
                "-lco", "DIM=2",
                "-nlt", "PROMOTE_TO_MULTI",
                shapeFile,
                "-a_srs", "EPSG:4269"
        );

        runCommand(params);
    }
