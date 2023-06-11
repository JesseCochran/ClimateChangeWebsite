package app;

import java.util.ArrayList;

public class PersonaData {
    private int personaId;
    private String name;
    private String quote;
    private String imagePath;
    private String requirements;
    private String experience;

    public PersonaData(int personaId, String name, String quote, String imagePath, String requirements,
            String experience) {
        this.personaId = personaId;
        this.name = name;
        this.quote = quote;
        this.imagePath = imagePath;
        this.requirements = requirements;
        this.experience = experience;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

}
