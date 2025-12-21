update BM77HUYP set SAKUJO_FLG = '1' ,
LAST_USER=/*userId*/,
LAST_TMSP =current timestamp
where TOUROKU_NO =/*tourokuNo*/'0001'
/*IF tourokuSeq !=null*/
and TOUROKU_SEQ =/*tourokuSeq*/'001'
/*END*/
/*IF dataVer !=null*/
and DATA_VER=/*dataVer*/'001'
/*END*/
/*IF nendo !=null*/
and NENDO = /*nendo*/''
/*END*/