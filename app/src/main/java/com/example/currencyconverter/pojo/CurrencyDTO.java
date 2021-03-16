package com.example.currencyconverter.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class CurrencyDTO implements Parcelable {
    private final String ID;
    private final String NumCode;
    private final String CharCode;
    private final int Nominal;
    private final String Name;
    private final double Value;
    private final double Previous;

    public CurrencyDTO(String ID, String numCode, String charCode, int nominal, String name, double value, double previous) {
        this.ID = ID;
        NumCode = numCode;
        CharCode = charCode;
        Nominal = nominal;
        Name = name;
        Value = value;
        Previous = previous;
    }

    protected CurrencyDTO(Parcel in) {
        ID = in.readString();
        NumCode = in.readString();
        CharCode = in.readString();
        Nominal = in.readInt();
        Name = in.readString();
        Value = in.readDouble();
        Previous = in.readDouble();
    }

    public static final Creator<CurrencyDTO> CREATOR = new Creator<CurrencyDTO>() {
        @Override
        public CurrencyDTO createFromParcel(Parcel in) {
            return new CurrencyDTO(in);
        }

        @Override
        public CurrencyDTO[] newArray(int size) {
            return new CurrencyDTO[size];
        }
    };

    public String getID() {
        return ID;
    }

    public String getNumCode() {
        return NumCode;
    }

    public String getCharCode() {
        return CharCode;
    }

    public int getNominal() {
        return Nominal;
    }

    public String getName() {
        return Name;
    }

    public double getValue() {
        return Value;
    }

    public double getPrevious() {
        return Previous;
    }

    @Override
    public String toString() {
        return "{" + CharCode + ": " + Value + "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(NumCode);
        dest.writeString(CharCode);
        dest.writeInt(Nominal);
        dest.writeString(Name);
        dest.writeDouble(Value);
        dest.writeDouble(Previous);
    }

//    public DTOCurrency(Parcel in) {
//        String[] data = new String[7];
//        in.readStringArray(data);
//
//        ID = data[0];
//        NumCode = data[1];
//        CharCode = data[2];
//        Nominal = Integer.parseInt(data[3]);
//        Name = data[4];
//        Value = Double.parseDouble(data[5]);
//        Previous = Double.parseDouble(data[6]);
//    }

//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeStringArray(new String[] {ID, NumCode, CharCode, String.valueOf(Nominal),
//                                Name, String.valueOf(Value), String.valueOf(Previous)});
//    }
//
//
//    public static final Creator<DTOCurrency> CREATOR = new Creator<DTOCurrency>() {
//        @Override
//        public DTOCurrency createFromParcel(Parcel in) {
//            return new DTOCurrency(in);
//        }
//
//        @Override
//        public DTOCurrency[] newArray(int size) {
//            return new DTOCurrency[size];
//        }
//};
}
