select
    bd01.company_cd,
    bd01.oner_cd,
    bm11.oner_name_kj,
    bd01.urikake_cd,
    tm11.oner_name_kj as urikake_name,
    bd01.keisan_ym,
    bd01.kei_category,
    bc27.kei_cate_name,
    bc27.ctrl_flg1,
    bc27.ctrl_flg2,
    bc27.ctrl_flg3,
    bc27.soat_key as sort_key1,
    bd01.kei_shu,
    bc28.kei_shu_name,
    rtrim(bc28.save_dir) as save_dir,
    bc28.disp_months,
    bc28.soat_key as sort_key2,
    bd01.hakko_dt,
    rtrim(bd01.file_name) as file_name

from 
    bd01keir bd01,
    tm11oner tm11,
    bm11oner bm11,
    bc27keic bc27,
    bc28keis bc28

where
    bd01.company_cd = bm11.company_cd
and bd01.oner_cd = bm11.oner_cd
and bd01.company_cd = tm11.company_cd
and bd01.urikake_cd = tm11.oner_cd
and bd01.kei_category = bc27.kei_category
and bd01.kei_category = bc28.kei_category
and bd01.kei_shu = bc28.kei_shu
and bd01.hakko_dt >= /*hakkoDt*/'20070601'
/*IF (onerCd != null) */
and bd01.company_cd = /*companyCd*/'00'
and bd01.oner_cd = /*onerCd*/'36007'
/*END*/
order by 
    sort_key1,
    bd01.oner_cd,
    sort_key2,
    bd01.urikake_cd,
    bd01.hakko_dt desc,
    bd01.keisan_ym desc