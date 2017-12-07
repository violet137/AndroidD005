package com.greenacademy.ga_finalprojecthm.server;

import android.util.Log;

import com.greenacademy.ga_finalprojecthm.model.ChiTietSanPham.ChiTietSanPham;
import com.greenacademy.ga_finalprojecthm.model.ChiTietSanPham.HinhByColor;
import com.greenacademy.ga_finalprojecthm.model.ChiTietSanPham.SanPhamTranfers;
import com.greenacademy.ga_finalprojecthm.model.DanhMucHang.DanhMucHang;
import com.greenacademy.ga_finalprojecthm.model.DanhMucHang.DanhMucHangTranfers;
import com.greenacademy.ga_finalprojecthm.model.DanhMucHang.DanhMucList;
import com.greenacademy.ga_finalprojecthm.model.FashionShopList;
import com.greenacademy.ga_finalprojecthm.model.FashionShop;
import com.greenacademy.ga_finalprojecthm.model.LoaiHoTro;
import com.greenacademy.ga_finalprojecthm.model.LoaiTapChiJson;
import com.greenacademy.ga_finalprojecthm.model.LoginDetails;
import com.greenacademy.ga_finalprojecthm.model.QuestionSupport;
import com.greenacademy.ga_finalprojecthm.model.RootLoaiHoTro;
import com.greenacademy.ga_finalprojecthm.model.RootLoaiTapChi;
import com.greenacademy.ga_finalprojecthm.model.RootSupport;
import com.greenacademy.ga_finalprojecthm.model.TapChiJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * Created by xuanson on 11/7/2017.
 */

public class ParsingToModelFromJSON {
    public static FashionShopList parseToFashionShopList(String strJSON) {
        FashionShopList root = new FashionShopList();
        try {
            JSONObject rootJson = new JSONObject(strJSON);

            int status = rootJson.getInt("Status");
            String des = rootJson.getString("Description");
            root.setStatus(status);
            root.setDescription(des);
            //
            JSONArray cuahangListJson = rootJson.getJSONArray("CuaHangTranfers");
            for (int i = 0; i < cuahangListJson.length(); i++) {
                JSONObject cuahangJson = cuahangListJson.getJSONObject(i);
                String name = cuahangJson.getString("TenCuaHang");
                String address = cuahangJson.getString("DiaChi");
                String style = cuahangJson.getString("LoaiThoiTrangData");
                Double lat = cuahangJson.getDouble("Lat");
                Double lng = cuahangJson.getDouble("Lng");
                Double evaluate = cuahangJson.getDouble("DanhGia");
                //
                FashionShop shop = new FashionShop();
                shop.setName(name);
                shop.setAddress(address);
                shop.setStyle(style);
                shop.setLat(lat);
                shop.setLng(lng);
                shop.setEvaluate(evaluate);
                root.getCuaHangTranfers().add(shop);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return root;
    }

    public static RootLoaiHoTro parseToLoaiHoTro(String strJSON) {
        RootLoaiHoTro rootLoaiHoTro = new RootLoaiHoTro();
        try {
            JSONObject rooJSON = new JSONObject(strJSON);

            int status = rooJSON.getInt("Status");
            String description = rooJSON.getString("Description");
            rootLoaiHoTro.setStatus(status);
            rootLoaiHoTro.setDescription(description);
            JSONArray loaihotro = rooJSON.getJSONArray("LoaiHoTroTranfers");
            for (int i = 0; i < loaihotro.length(); i++) {
                JSONObject hoTroJson = loaihotro.getJSONObject(i);
                String id = hoTroJson.getString("IdHoTro");
                String tenloaihotro = hoTroJson.getString("TenHoTro");
                LoaiHoTro loaiHoTro = new LoaiHoTro();
                loaiHoTro.setIdHoTro(id);
                loaiHoTro.setTenHoTro(tenloaihotro);
                rootLoaiHoTro.getLoaiHoTroTranfers().add(loaiHoTro);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rootLoaiHoTro;
    }

    public static RootSupport parseToQuestion(String strJSON) {
        RootSupport rootSupport = new RootSupport();
        try {
            JSONObject rootJSON = new JSONObject(strJSON);

            int status = rootJSON.getInt("Status");
            String description = rootJSON.getString("Description");
            rootSupport.setStatus(status);
            rootSupport.setDescription(description);
            JSONArray bocauhoi = rootJSON.getJSONArray("CauHoiTranfers");
            for (int i = 0; i < bocauhoi.length(); i++) {
                JSONObject cauhoiJSON = bocauhoi.getJSONObject(i);
                int id = cauhoiJSON.getInt("Id");
                String noidungQuestion = cauhoiJSON.getString("NoiDungCauHoi");
                String traloi = cauhoiJSON.getString("TraLoi");
                String html = cauhoiJSON.getString("Html");
                QuestionSupport cauhoi = new QuestionSupport();
                cauhoi.setId(id);
                cauhoi.setNoiDungCauHoi(noidungQuestion);
                cauhoi.setTraLoi(traloi);
                cauhoi.setHtml(html);
                rootSupport.getCauHoiTranfers().add(cauhoi);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rootSupport;
    }

    public static LoginDetails parseToLoginDetails(String strJSON) {
        LoginDetails login = new LoginDetails();
        try {
            JSONObject jsonObject1 = new JSONObject(strJSON);

            if (jsonObject1.has("Token"))
                login.setToken(jsonObject1.getString("Token"));
            if (jsonObject1.has("Description"))
                login.setDescription(jsonObject1.getString("Description"));
            if (jsonObject1.has("Status"))
                login.setStatus(jsonObject1.getInt("Status"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return login;
    }

    public static RootLoaiTapChi parseToLoaiTapChiList(String strJSON) {
        RootLoaiTapChi rootLoaiTapChi = new RootLoaiTapChi();
        try {
            //JsonObject, JSONArray đại diện cho JSON
            JSONObject rootJson = new JSONObject(strJSON);

            int status = rootJson.getInt("Status");
            String description = rootJson.getString("Description");

            rootLoaiTapChi.setStatus(status);
            rootLoaiTapChi.setDescription(description);

            JSONArray loaiTapChiListJSON = rootJson.getJSONArray("LoaiTapChiTranfers");
            for (int i = 0; i < loaiTapChiListJSON.length(); i++) {
                JSONObject loaiTapChiJSON = loaiTapChiListJSON.getJSONObject(i);
                String LoaiTapChi = loaiTapChiJSON.getString("LoaiTapChi");
                String Ten = loaiTapChiJSON.getString("Ten");

                LoaiTapChiJson loaiTapChiJson = new LoaiTapChiJson();
                loaiTapChiJson.setLoaiTapChi(LoaiTapChi);
                loaiTapChiJson.setTen(Ten);

                rootLoaiTapChi.getLoaiTapChiTranfers().add(loaiTapChiJson);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rootLoaiTapChi;
    }

    public static TapChiJson parseToTapChi(String strJSON) {
        TapChiJson rootChiTietTapChi = new TapChiJson();
        try {
            //JsonObject, JSONArray đại diện cho JSON
            JSONObject rootJson = new JSONObject(strJSON);

            int Status = rootJson.getInt("Status");
            String Description = rootJson.getString("Description");

            rootChiTietTapChi.setStatus(Status);
            rootChiTietTapChi.setDescription(Description);

            int Id = rootJson.getJSONObject("TapChi").getInt("Id");
            String Ten = rootJson.getJSONObject("TapChi").getString("Ten");
            String MoTa = rootJson.getJSONObject("TapChi").getString("Mota");
            String LoaiTapChi = rootJson.getJSONObject("TapChi").getString("LoaiTapChi");
            String HinhDaiDien = rootJson.getJSONObject("TapChi").getString("HinhDaiDien");
            String NoiDung = rootJson.getJSONObject("TapChi").getString("NoiDung");

            rootChiTietTapChi.setIdTapChi(Id);
            rootChiTietTapChi.setTen(Ten);
            rootChiTietTapChi.setMoTa(MoTa);
            rootChiTietTapChi.setLoaiTapChi(LoaiTapChi);
            rootChiTietTapChi.setLinkHinh(HinhDaiDien);
            rootChiTietTapChi.setNoiDung(NoiDung);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rootChiTietTapChi;
    }


    public static DanhMucHang parseToDanhMucHang(String strJson) {
        //tao doi tuong danh muc hang de gan tu json oject xuong
        DanhMucHang danhMucHang = new DanhMucHang();


        try {
            // tao doi tuong jsonOject
            JSONObject rootJSon = new JSONObject(strJson);// khoi tao bien status va lay tu jsonObject va gan vao danhMucHang
            int status;
            status = rootJSon.getInt("Status");
            danhMucHang.setStatus(status);
            // khoi tao bien string decription lay tu json xuong va gan vao doi tuong danhmuchang
            String decription;
            decription = rootJSon.getString("Description");
            danhMucHang.setDescription(decription);
            // khoi tao doi tuong DanhMucHangTranfers lay tu sever xuong va gan vao
            DanhMucHangTranfers danhMucHangTranfers = new DanhMucHangTranfers();
            // lay doi tuong DanhMucHangTranfers tu sever xuong.
            JSONObject danhMucHangTranferJSon = rootJSon.getJSONObject("DanhMucHangTranfers");
            // lay cac doi tuong trong jsonObject cua DanhMucHangTranfer ra
            int xuHuongTTrangID = danhMucHangTranferJSon.getInt("XuHuongTtrangId");
            String loaiTTrang = danhMucHangTranferJSon.getString("LoaiThoiTrang");
            String xuHuongTTrangLink = danhMucHangTranferJSon.getString("XuHuongTtrangLink");
            // tao 1 arrayList DanhMucList gan no vo
            JSONArray danhMucListJson = danhMucHangTranferJSon.getJSONArray("DanhMucList");
            ArrayList<DanhMucList> danhMucListsTemp = new ArrayList<>();
            for (int i = 0; i < danhMucListJson.length(); i++) {
                DanhMucList temp = new DanhMucList();
                temp.setId(danhMucListJson.getJSONObject(i).getInt("Id"));
                temp.setLoaiThoiTrang(danhMucListJson.getJSONObject(i).getString("LoaiThoiTrang"));
                temp.setTenDanhMuc(danhMucListJson.getJSONObject(i).getString("TenDanhMuc"));
                danhMucListsTemp.add(temp);
            }
            // gan cac doi tuong vua lay duoc vao DanhMucHangtranfers
            danhMucHangTranfers.setDanhMucLists(danhMucListsTemp);
            danhMucHangTranfers.setLoaiThoiTrang(loaiTTrang);
            danhMucHangTranfers.setXuHuongTtrangId(xuHuongTTrangID);
            danhMucHangTranfers.setXuHuongTtrangLink(xuHuongTTrangLink);
            //gan doi tuong danhMucHangTranfers vao doi tuong danhMucHang
            danhMucHang.setDanhMucHangTranfers(danhMucHangTranfers);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return danhMucHang;
    }

    public static ChiTietSanPham parseToChiTietSanPham(String strJson) {
        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
        ArrayList<SanPhamTranfers> listSanPhamTranfer=new ArrayList<>();
        try {
            JSONObject rootJson = new JSONObject(strJson);
            // lay status va decriptions
            int Status;
            String Descriptions;
            Status = rootJson.getInt("Status");
            Descriptions = rootJson.getString("Description");
            chiTietSanPham.setDescription(Descriptions);
            chiTietSanPham.setStatus(Status);
            //lay sanPhamTranfers


            JSONArray sanPhamTranfersJSOn = rootJson.getJSONArray("SanPhamTranfers");


            ArrayList<String> tempListMau = new ArrayList<>();
            ArrayList<String> tempListSize = new ArrayList<>();


            int[] tempSPPhuHop;
            for (int i = 0; i < sanPhamTranfersJSOn.length(); i++) {
                ArrayList<HinhByColor> tempListHinhByColor = new ArrayList<>();
                SanPhamTranfers tempSanPhamTranfers = new SanPhamTranfers();
                tempSanPhamTranfers.setID(sanPhamTranfersJSOn.getJSONObject(i).getInt("Id"));
                tempSanPhamTranfers.setTen(sanPhamTranfersJSOn.getJSONObject(i).getString("Ten"));
                tempSanPhamTranfers.setNgaytao(sanPhamTranfersJSOn.getJSONObject(i).getString("NgayTao"));
                tempSanPhamTranfers.setGiaTien(sanPhamTranfersJSOn.getJSONObject(i).getInt("GiaTien"));
                tempSanPhamTranfers.setGiaTienGiam(sanPhamTranfersJSOn.getJSONObject(i).getInt("GiaTienGiam"));
                tempSanPhamTranfers.setMoTa(sanPhamTranfersJSOn.getJSONObject(i).getString("MoTa"));
                tempSanPhamTranfers.setChiTiet(sanPhamTranfersJSOn.getJSONObject(i).getString("ChiTiet"));
                //set mau
                JSONArray mauSacJSon = sanPhamTranfersJSOn.getJSONObject(i).getJSONArray("MauSac");
                for (int j = 0; j < mauSacJSon.length(); j++) {
                    tempListMau.add(mauSacJSon.get(j).toString());
                }
                tempSanPhamTranfers.setMauSac(tempListMau);
                //set size
                JSONArray sizeJSon = sanPhamTranfersJSOn.getJSONObject(i).getJSONArray("Size");
                for (int j = 0; j < sizeJSon.length(); j++) {
                    tempListSize.add(sizeJSon.get(j).toString());
                }
                tempSanPhamTranfers.setSize(tempListSize);
                //set san pham phu hop
                JSONArray spPhuHopJSon = sanPhamTranfersJSOn.getJSONObject(i).getJSONArray("SpPhuHop");
                tempSPPhuHop = new int[spPhuHopJSon.length()];
                for (int j = 0; j < spPhuHopJSon.length(); j++) {
                    tempSPPhuHop[j] = parseInt(spPhuHopJSon.get(j).toString());
                }
                tempSanPhamTranfers.setSapPhamPhuHop(tempSPPhuHop);
                //set link hinh
                JSONArray linkHinhObjectJSon = sanPhamTranfersJSOn.getJSONObject(i).getJSONArray("LinkHinh");
                for(int j=0;j<linkHinhObjectJSon.length();j++){
                    HinhByColor tempHinhByColor =new HinhByColor();
                    JSONArray arrLinkHinhByColorJSon=linkHinhObjectJSon.getJSONObject(j).getJSONArray("LinkHinh");
                    ArrayList<String> strLinkHinhByColor=new ArrayList<>();
                    for(int k=0;k<arrLinkHinhByColorJSon.length();k++){
                        strLinkHinhByColor.add(arrLinkHinhByColorJSon.get(k).toString());
                    }
                    tempHinhByColor.setLinkHinh(strLinkHinhByColor);
                    String tempMau=linkHinhObjectJSon.getJSONObject(j).getString("MauSac");
                    tempHinhByColor.setMauSac(tempMau);
                    tempListHinhByColor.add(tempHinhByColor);

                }

                tempSanPhamTranfers.setHinhByColors(tempListHinhByColor);
                listSanPhamTranfer.add(tempSanPhamTranfers);
            }
            chiTietSanPham.setSanPhamTranfers(listSanPhamTranfer);

        } catch (JSONException e) {
            Log.e("loi nay ne",e.toString());
            e.printStackTrace();
        }

        return chiTietSanPham;
    }
}
