update BT24MLKJ

set COURSE_STATUS  = /*entity.courseStatus*/'2'
,   COMPLE_COURSE_DT  = /*entity.compleCourseDt*/'99991231'
,   COMPLE_POINT  = /*entity.complePoint*/0
,   COMPLE_COURSE_CD  = /*entity.compleCourseCd*/'99'
,   COMPLE_COURSE_NAME  = /*entity.compleCourseName*/''
,   LAST_USER  = /*entity.lastUser*/''
,   LAST_PGM  = /*entity.lastPgm*/'BSM006'
,   LAST_TMSP  = /*entity.lastTmsp*/

where STAFF_ID = /*entity.staffId*/'99999999'
AND   COURSE_STATUS <> '1'