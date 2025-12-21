update BM76SEID set SAKUJO_FLG = '1' ,
LAST_USER=/*userId*/,
LAST_TMSP =current timestamp
where TOUROKU_NO =/*tourokuNo*/'0001'
/*IF dataVer!=null */
and DATA_VER=/*dataVer*/'001'
/*END*/