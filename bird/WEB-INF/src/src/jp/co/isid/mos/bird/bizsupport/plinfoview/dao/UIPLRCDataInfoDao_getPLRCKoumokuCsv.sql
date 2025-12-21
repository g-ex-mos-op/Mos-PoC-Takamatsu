select distinct 
    rtrim(KOUMOKU_NO) as KOUMOKU_NO,
    DISP_ORDER,
    rtrim(KOUMOKU_NAME) as KOUMOKU_NAME,
    rtrim(KOUMOKU_ZOKU) as KOUMOKU_ZOKU,
    rtrim(DISP_POSITION) as DISP_POSITION 
from
    WT54PLRC 
where
    COMPANY_CD = /*companyCd*/'00' 
and PL_YM between /*plYmZen*/'200502' and /*plYm*/'200602' 
and MISE_CD = /*miseCd*/'02001'
order by 
    DISP_ORDER
