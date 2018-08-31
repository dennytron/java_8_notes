private Properties appConfig = new Properties();
private Properties dbConfig = new Properties();

Config() {
    var propertiesURL = ClassLoader.getSystemClassLoader().getResource("paths.properties");
    var propertiesDB = ClassLoader.getSystemClassLoader().getResource("database.properties");

    try {
        assert propertiesURL != null;
        appConfig.load(propertiesURL.openStream());

        assert propertiesDB != null;
        dbConfig.load(propertiesDB.openStream());
    }
    catch (IOException ex) {
        ex.printStackTrace();
    }
}
