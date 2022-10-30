package com.codegym.dao;

import com.codegym.model.Medical;

import java.util.Arrays;
import java.util.List;

public class MedicalDao {
    private static Medical medical = new Medical();
    private static final List<String> listGender = Arrays.asList("Nam","Nữ","Khác");
    private static final List<String> listCountry = Arrays.asList("Việt Nam","Lào","CamPuChia","Trung Quốc","Nhật Bản","Anh","Nga");

    private static final List<String> listTranfer = Arrays.asList("Máy bay","Tàu lửa","Ô tô", "Khác");

    private static final List<String> listProvince = Arrays.asList("Thừa Thiên Huế","Quảng Bình","Quảng Trị", "Đà Nẵng");
    private static final List<String> listDistrict = Arrays.asList("Vĩnh Linh","TP Đông Hà","Đông Hà", "Cam Lộ");
    private static final List<String> listTown = Arrays.asList("Vĩnh Long","Vĩnh Linh","Hồ Xá", "Gio Linh");



    static {

    }
    public static Medical getMedical(){
        return medical;
    }
    public static void updateMedical(Medical newMedical) {
        medical = newMedical;
    }
    public static List<String> getListGender(){
        return listGender;
    }
    public static List<String> getListCountry(){
        return listCountry;
    }
    public static List<String> getListTranfer(){
        return listTranfer;
    }
    public static List<String> getListProvince(){
        return listProvince;
    }
    public static List<String> getListDistrict(){
        return listDistrict;
    }
    public static List<String> getListTown(){
        return listTown;
    }

}
