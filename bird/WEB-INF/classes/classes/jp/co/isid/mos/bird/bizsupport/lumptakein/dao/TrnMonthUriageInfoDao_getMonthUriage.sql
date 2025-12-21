select
    BT61.uriage
from
    BT61ZNDM as BT61
where
    BT61.EIGYO_DT = /*eigyoDt*/'200603' and
    BT61.COMPANY_CD = '00' and
    BT61.MISE_CD = /*miseCd*/'00018'
