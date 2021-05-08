package com.example.thebug_zoo.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Species implements Parcelable {

    public int _id;
    public int id;
    public int armario;
    public int estante;
    public String ordem;
    public String familia;
    public String identificacao;
    public String inf_adicionais;
    public String fonte;
    public String coletor;
    public String _local;
    public String _data;

    public Species(){

    }

    public Species(int _id, int id, int armario, int estante, String ordem, String familia, String identificacao, String inf_adicionais, String fonte, String coletor, String _local, String _data){

        this._id = _id;
        this.id = id;
        this.armario = armario;
        this.estante = estante;
        this.ordem = ordem;
        this.familia = familia;
        this.identificacao = identificacao;
        this.inf_adicionais = inf_adicionais;
        this.fonte = fonte;
        this._local = _local;
        this._data = _data;

    }

    protected Species(Parcel in) {
        _id = in.readInt();
        id = in.readInt();
        armario = in.readInt();
        estante = in.readInt();
        ordem = in.readString();
        familia = in.readString();
        identificacao = in.readString();
        inf_adicionais = in.readString();
        fonte = in.readString();
        coletor = in.readString();
        _local = in.readString();
        _data = in.readString();
    }

    public static final Creator<Species> CREATOR = new Creator<Species>() {
        @Override
        public Species createFromParcel(Parcel in) {
            return new Species(in);
        }

        @Override
        public Species[] newArray(int size) {
            return new Species[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeInt(id);
        dest.writeInt(armario);
        dest.writeInt(estante);
        dest.writeString(ordem);
        dest.writeString(familia);
        dest.writeString(identificacao);
        dest.writeString(inf_adicionais);
        dest.writeString(fonte);
        dest.writeString(coletor);
        dest.writeString(_local);
        dest.writeString(_data);
    }
}
