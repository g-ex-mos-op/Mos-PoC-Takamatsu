select 
    rtrim(COMPANY_CD) as COMPANY_CD,
    rtrim(PL_YM) as PL_YM,
    rtrim(KOUMOKU_NO) as KOUMOKU_NO,
    DISP_ORDER,
    rtrim(KOUMOKU_NAME) as KOUMOKU_NAME,
    rtrim(KOUMOKU_ZOKU) as KOUMOKU_ZOKU,
    KINGAKU,
    rtrim(DISP_COLOR) as DISP_COLOR,
    rtrim(DISP_POSITION) as DISP_POSITION,
    rtrim(KOUSEIHI_MOTO) as KOUSEIHI_MOTO
from
    WT54PLRC 
where
    COMPANY_CD = /*companyCd*/'00' 
and PL_YM between /*plYmZen*/'200502' and /*plYm*/'200602' 
and MISE_CD = /*miseCd*/'02001'
order by 
    DISP_ORDER,
    PL_YM 