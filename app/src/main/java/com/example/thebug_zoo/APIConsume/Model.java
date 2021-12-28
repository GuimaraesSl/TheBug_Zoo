package com.example.thebug_zoo.APIConsume;

public class Model {
    exemplary exemplary;

    public Model.exemplary getExemplary() {
        return exemplary;
    }

    public void setExemplary(Model.exemplary exemplary) {
        this.exemplary = exemplary;
    }

    public class exemplary{
        String _id;

        public String[] getImages() {
            return images;
        }

        public void setImages(String[] images) {
            this.images = images;
        }

        String[] images;
        int __v;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }
    }

}
