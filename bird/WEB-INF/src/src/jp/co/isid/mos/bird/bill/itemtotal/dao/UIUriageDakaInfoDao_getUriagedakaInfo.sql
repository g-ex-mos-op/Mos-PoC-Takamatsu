select
    DECIMAL(BT61.URIAGE, 15, 0) as URIAGE_DOGETU
from
    BT61ZNDM as BT61
where
    BT61.COMPANY_CD = '00' and
    BT61.MISE_CD = /*miseCd*/'04471' and
    BT61.EIGYO_DT = /*eigyoDt*/'200606'
