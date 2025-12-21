update BT26UPJK

set RENEW3_ENT_YEAR  = /*entity.entryYear*/'' 
,   RENEW3_ENT_KAI  = /*entity.entryKai*/'' 
,   RENEW3_COURSE  = /*entity.compleCourseCd*/''
,   RENEW3_COURSE_NAME  = /*entity.compleCourseName*/''
,   RENEW3_DATE  = /*entity.compleCourseDt*/''
,   RENEW3_STATUS  = /*entity.courseStatus*/''
,   LAST_USER  = /*entity.lastUser*/''
,   LAST_PGM  = /*entity.lastPgm*/'BSM006'
,   LAST_TMSP  = /*entity.lastTmsp*/

where STAFF_ID = /*entity.staffId*/''
and   LICENSE_DT = /*entity.licenseDt*/''
and   RENEW3_STATUS <> '1'