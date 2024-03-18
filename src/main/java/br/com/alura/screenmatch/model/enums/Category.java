package br.com.alura.screenmatch.model.enums;

public enum Category {

    ACAO("Action", "Ação"),
    ROMANCE("Romance","Romance"),
    COMEDIA("Comedy","Comédia"),
    DRAMA("Drama","Drama"),
    CRIME("Crime","Crime");

    private final String categoryOmd;
    private final String categoryUser;

    Category(String categoryOmd, String categoryUser) {
        this.categoryOmd = categoryOmd;
        this.categoryUser = categoryUser;
    }

    public static Category  fromString(String text){
        for (Category category : Category.values()) {
            if (category.categoryOmd.equalsIgnoreCase(text) || category.categoryUser.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

}
