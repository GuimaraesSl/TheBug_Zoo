package com.example.thebug_zoo.APIConsume;

public class Model {
    exemplary exemplary;

    public Model.exemplary getExemplary() {
        return exemplary;
    }

    public static class exemplary{

        public String[] getImages() {
            return images;
        }

        String[] images;

    }

}
