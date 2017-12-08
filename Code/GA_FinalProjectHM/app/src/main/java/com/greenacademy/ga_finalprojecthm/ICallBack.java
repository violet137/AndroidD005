package com.greenacademy.ga_finalprojecthm;

import com.greenacademy.ga_finalprojecthm.model.RootLoaiTapChi;
import com.greenacademy.ga_finalprojecthm.model.RootTapChi;
import com.greenacademy.ga_finalprojecthm.model.TapChiJson;

/**
 * Created by hiang on 10/31/2017.
 */

public interface ICallBack {
    void GetLoaiThoiTrang(RootLoaiTapChi rootLoaiTapChi);
    void GetTapChi(RootTapChi rootTapChi);
    void GetChiTietTapChi(TapChiJson tapChiJson);


}

