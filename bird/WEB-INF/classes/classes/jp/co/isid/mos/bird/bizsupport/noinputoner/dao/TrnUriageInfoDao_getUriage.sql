select
    BT61.URIAGE,
    BT61.MISE_CD,
    BT61.COMPANY_CD,
    BT61.EIGYO_DT
from
    BT61ZNDM as BT61
where
    BT61.EIGYO_DT = /*eigyoDt*/'200603' and
    BT61.COMPANY_CD = '00' and
    BT61.MISE_CD in /*miseCd*/('00018')
