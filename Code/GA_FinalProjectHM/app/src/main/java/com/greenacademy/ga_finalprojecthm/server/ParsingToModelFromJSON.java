package com.greenacademy.ga_finalprojecthm.server;

import com.greenacademy.ga_finalprojecthm.model.fashionset.FashionSet;
import com.greenacademy.ga_finalprojecthm.model.fashionset.FashionSetList;
import com.greenacademy.ga_finalprojecthm.model.fashionset.HinhTranfer;
import com.greenacademy.ga_finalprojecthm.model.fashionset.SanPhamTranfer;
import com.greenacademy.ga_finalprojecthm.model.fashionshop.FashionShopList;
import com.greenacademy.ga_finalprojecthm.model.fashionshop.FashionShop;
import com.greenacademy.ga_finalprojecthm.model.LoaiHoTro;
import com.greenacademy.ga_finalprojecthm.model.LoaiTapChiJson;
import com.greenacademy.ga_finalprojecthm.model.LoginDetails;
import com.greenacademy.ga_finalprojecthm.model.QuestionSupport;
import com.greenacademy.ga_finalprojecthm.model.RootLoaiHoTro;
import com.greenacademy.ga_finalprojecthm.model.RootLoaiTapChi;
import com.greenacademy.ga_finalprojecthm.model.RootSupport;
import com.greenacademy.ga_finalprojecthm.model.TapChiJson;
import com.greenacademy.ga_finalprojecthm.model.fashionset.SetDoTranfer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xuanson on 11/7/2017.
 */

public class ParsingToModelFromJSON {
    public static FashionSetList parseToFashionSetList(String strJSON){
        FashionSetList rootFashionSet = new FashionSetList();
        try {
            JSONObject rootJson = new JSONObject(strJSON);
            int status = rootJson.getInt("Status");
            String des = rootJson.getString("Description");
            rootFashionSet.setStatus(status);
            rootFashionSet.setDescription(des);
            //
            JSONArray xuhuongListJson = rootJson.getJSONArray("XuHuongTtrangTranfer ");
            for (int i=0;i<xuhuongListJson.length();i++) {
                JSONObject xuhuongJson = xuhuongListJson.getJSONObject(i);
                int idXuhuong = xuhuongJson.getInt("IdXuHuong ");
                String hinh = xuhuongJson.getString("HinhDaiDien  ");
                String loaittrang = xuhuongJson.getString("LoaiThoiTrang ");
                //
                JSONArray setdoListJson = xuhuongJson.getJSONArray("SetDoTranfer ");
                int idSp = 0;
                float giatien = 0;
                String linkHinh = null;
                int idSet = 0;
                String hinhSet = null;
                for (int j = 0; j < setdoListJson.length(); j++) {
                    JSONObject setdoJson = setdoListJson.getJSONObject(i);
                    idSet = setdoJson.getInt("Id ");
                    hinhSet = setdoJson.getString("HinhDaiDien ");
                    //
                    JSONArray sanphamListJson = setdoJson.getJSONArray("SanPhamTranfer  ");
                    for (int k = 0; k < setdoListJson.length(); k++) {
                        JSONObject sanphamJson = sanphamListJson.getJSONObject(i);
                        idSp = sanphamJson.getInt("Id ");
                        giatien = sanphamJson.getInt("GiaTien ");
                        //
                        JSONArray hinhListJson = sanphamJson.getJSONArray("HinhByColor ");
                        for (int l = 0; l < hinhListJson.length(); l++) {
                            JSONObject hinhJson = hinhListJson.getJSONObject(i);
                            linkHinh = hinhJson.getString("LinkHinh ");
                            //set data HinhTranfer
//                            HinhTranfer h = new HinhTranfer();
//                            h.setLinkHinh(linkHinh);
                        }
                    }
                };
                FashionSet fashionSet = new FashionSet();
                SetDoTranfer sd = new SetDoTranfer();
                //set data SanPhamTranfer
                SanPhamTranfer sp = new SanPhamTranfer();
                sp.setIdSp(idSp);
                sp.setGiatien(giatien);
                sp.setLinkhinh(linkHinh);
                sd.getSanPhamTranferArrayList().add(sp);
                //set data SetDoTranfer
                sd.setIdSet(idSet);
                sd.setHinhSet(hinhSet);
                fashionSet.getSetDoTranferArrayList().add(sd);
                //set data FashionSet
                fashionSet.setIdXuHuong(idXuhuong);
                fashionSet.setHinh(hinh);
                fashionSet.setLoaithoitrang(loaittrang);
                rootFashionSet.getXuHuongTrangTranfers().add(fashionSet);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rootFashionSet;
    }

    public static FashionShopList parseToFashionShopList(String strJSON) {
        FashionShopList rootFashionShop = new FashionShopList();
        try {
            JSONObject rootJson = new JSONObject(strJSON);

            int status = rootJson.getInt("Status");
            String des = rootJson.getString("Description");
            rootFashionShop.setStatus(status);
            rootFashionShop.setDescription(des);
            //
            JSONArray cuahangListJson = rootJson.getJSONArray("CuaHangTranfers");
            for (int i =0; i <cuahangListJson.length(); i++){
                JSONObject cuahangJson = cuahangListJson.getJSONObject(i);
                String name = cuahangJson.getString("TenCuaHang");
                String address = cuahangJson.getString("DiaChi");
                String hours = cuahangJson.getString("GioMoCua");
                String style = "";
                //
                JSONArray styleArray = cuahangJson.getJSONArray("LoaiThoiTrang");
                for (int j=0; j<styleArray.length();j++){
                    JSONObject styleJson = styleArray.getJSONObject(j);
                    style += styleJson.getString("Ten") + " - ";
                }
                if (!style.isEmpty()){
                    style = style.substring(0,style.length()-3);
                }
                Double lat = cuahangJson.getDouble("Lat");
                Double lng = cuahangJson.getDouble("Lng");
                Double evaluate = cuahangJson.getDouble("DanhGia");
                //
                FashionShop shop = new FashionShop();
                shop.setName(name);
                shop.setAddress(address);
                shop.setStyle(style);
                shop.setHours(hours);
                shop.setLat(lat);
                shop.setLng(lng);
                shop.setEvaluate(evaluate);
                rootFashionShop.getCuaHangTranfers().add(shop);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rootFashionShop;
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
}
