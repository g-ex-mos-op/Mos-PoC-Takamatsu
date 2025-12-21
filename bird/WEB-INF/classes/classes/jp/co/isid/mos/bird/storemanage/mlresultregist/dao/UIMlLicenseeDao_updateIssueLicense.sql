update
    BT26UPJK as BT26
set
    BT26.LICENSE_KBN = /*entity.licenseKbn*/'01',
    BT26.LICENSE_NO = /*entity.licenseNo*/'1234567890',
    BT26.LICENSE_DT = /*entity.licenseDt*/'200704',
    BT26.LICENSE_UP_DT = /*entity.licenseUpDt*/'200704',
    BT26.LICENSE_VALID_DT = /*entity.licenseValidDt*/'201003',
    BT26.EXPIRE_FLG = /*entity.expireFlg*/'0',
    BT26.LAST_USER = /*entity.lastUser*/'99990003',
    BT26.LAST_PGM = /*entity.lastPgm*/'BSM008',
    BT26.LAST_TMSP = /*entity.lastTmsp*/'2006-08-25 15:48:00.000000'
where
    BT26.STAFF_ID =/*entity.staffId*/'12345678'