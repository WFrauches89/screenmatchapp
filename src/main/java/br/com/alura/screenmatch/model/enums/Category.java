package br.com.alura.screenmatch.model.enums;

public enum Category {

    ACAO("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    CRIME("Crime");

    private final String categoryOmd;

    Category(String categoryOmd) {
        this.categoryOmd = categoryOmd;
    }

    public static Category  fromString(String text){
        for (Category category : Category.values()) {
            if (category.categoryOmd.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }



    }
