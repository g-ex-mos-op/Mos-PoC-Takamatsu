update BT30ENKJ 

set COURSE_STATUS  = /*entity.courseStatus*/'2'
,   COMPLE_COURSE_DT  = /*entity.compleCourseDt*/'99991231'
,   COMPLE_POINT  = 0
,   COMPLE_COURSE_CD  = /*entity.compleCourseCd*/'99'
,   COMPLE_COURSE_NAME  = /*entity.compleCourseName*/''
,   LAST_USER  = /*entity.lastUser*/''
,   LAST_PGM  = /*entity.lastPgm*/'BSM006'
,   LAST_TMSP  = /*entity.lastTmsp*/

where ENTRY_CD = /*entity.entryCd*/'01'
AND   ENTRY_YEAR = /*entity.entryYear*/'9999'
AND   ENTRY_KAI  = /*entity.entryKai*/'001'
AND   STAFF_ID = /*entity.staffId*/'99999999'
