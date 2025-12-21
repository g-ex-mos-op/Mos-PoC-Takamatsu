delete from
    BT17PLDT as BT17
where
    BT17.PL_TYPE = /*trnPLInfo.plType*/'0' and
    BT17.PL_YM = /*trnPLInfo.plYm*/'200603' and
    BT17.COMPANY_CD = /*trnPLInfo.companyCd*/'00' and
    BT17.MISE_CD = /*trnPLInfo.miseCd*/'36005'
