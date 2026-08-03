INSERT INTO idm.permission (id, module, code, title, active) VALUES

-- =========================
-- STUDENT
-- =========================
(gen_random_uuid(),'student','create','Create Student',true),
(gen_random_uuid(),'student','read','Read Student',true),
(gen_random_uuid(),'student','update','Update Student',true),
(gen_random_uuid(),'student','delete','Delete Student',true),
(gen_random_uuid(),'student','list','List Students',true),
(gen_random_uuid(),'student','search','Search Students',true),
(gen_random_uuid(),'student','export','Export Students',true),

-- =========================
-- PARENT
-- =========================
(gen_random_uuid(),'parent','create','Create Parent',true),
(gen_random_uuid(),'parent','read','Read Parent',true),
(gen_random_uuid(),'parent','update','Update Parent',true),
(gen_random_uuid(),'parent','delete','Delete Parent',true),
(gen_random_uuid(),'parent','list','List Parents',true),
(gen_random_uuid(),'parent','search','Search Parents',true),

-- =========================
-- ACCOUNT
-- =========================
(gen_random_uuid(),'account','create','Create Account',true),
(gen_random_uuid(),'account','read','Read Account',true),
(gen_random_uuid(),'account','update','Update Account',true),
(gen_random_uuid(),'account','delete','Delete Account',true),
(gen_random_uuid(),'account','list','List Accounts',true),
(gen_random_uuid(),'account','search','Search Accounts',true),
(gen_random_uuid(),'account','lock','Lock Account',true),
(gen_random_uuid(),'account','unlock','Unlock Account',true),
(gen_random_uuid(),'account','reset-password','Reset Password',true),
(gen_random_uuid(),'account','change-password','Change Password',true),
(gen_random_uuid(),'account','assign-role','Assign Role',true),

-- =========================
-- PRODUCT
-- =========================
(gen_random_uuid(),'product','create','Create Product',true),
(gen_random_uuid(),'product','read','Read Product',true),
(gen_random_uuid(),'product','update','Update Product',true),
(gen_random_uuid(),'product','delete','Delete Product',true),
(gen_random_uuid(),'product','list','List Products',true),
(gen_random_uuid(),'product','search','Search Products',true),
(gen_random_uuid(),'product','publish','Publish Product',true),
(gen_random_uuid(),'product','archive','Archive Product',true),
(gen_random_uuid(),'product','assign','Assign Product',true),

-- =========================
-- ASSESSMENT
-- =========================
(gen_random_uuid(),'assessment','create','Create Assessment',true),
(gen_random_uuid(),'assessment','read','Read Assessment',true),
(gen_random_uuid(),'assessment','update','Update Assessment',true),
(gen_random_uuid(),'assessment','delete','Delete Assessment',true),
(gen_random_uuid(),'assessment','list','List Assessments',true),
(gen_random_uuid(),'assessment','start','Start Assessment',true),
(gen_random_uuid(),'assessment','submit','Submit Assessment',true),
(gen_random_uuid(),'assessment','approve','Approve Assessment',true),
(gen_random_uuid(),'assessment','reject','Reject Assessment',true),
(gen_random_uuid(),'assessment','publish','Publish Assessment',true),

-- =========================
-- BASE
-- =========================
(gen_random_uuid(),'base','read','Read Base Data',true),
(gen_random_uuid(),'base','update','Update Base Data',true),
(gen_random_uuid(),'base','list','List Base Data',true),
(gen_random_uuid(),'base','manage','Manage Base Data',true),

-- =========================
-- SESSION
-- =========================
(gen_random_uuid(),'session','create','Create Session',true),
(gen_random_uuid(),'session','read','Read Session',true),
(gen_random_uuid(),'session','update','Update Session',true),
(gen_random_uuid(),'session','delete','Delete Session',true),
(gen_random_uuid(),'session','list','List Sessions',true),
(gen_random_uuid(),'session','start','Start Session',true),
(gen_random_uuid(),'session','end','End Session',true),
(gen_random_uuid(),'session','attendance','Manage Attendance',true),
(gen_random_uuid(),'session','evaluate','Evaluate Session',true),

-- =========================
-- TEACHER
-- =========================
(gen_random_uuid(),'teacher','create','Create Teacher',true),
(gen_random_uuid(),'teacher','read','Read Teacher',true),
(gen_random_uuid(),'teacher','update','Update Teacher',true),
(gen_random_uuid(),'teacher','delete','Delete Teacher',true),
(gen_random_uuid(),'teacher','list','List Teachers',true),
(gen_random_uuid(),'teacher','search','Search Teachers',true),
(gen_random_uuid(),'teacher','assign-class','Assign Class',true),

-- =========================
-- SCHOOL
-- =========================
(gen_random_uuid(),'school','create','Create School',true),
(gen_random_uuid(),'school','read','Read School',true),
(gen_random_uuid(),'school','update','Update School',true),
(gen_random_uuid(),'school','delete','Delete School',true),
(gen_random_uuid(),'school','list','List Schools',true),
(gen_random_uuid(),'school','settings','School Settings',true),
(gen_random_uuid(),'school','statistics','School Statistics',true),
(gen_random_uuid(),'school','manage','Manage School',true),

-- =========================
-- AI REPORT
-- =========================
(gen_random_uuid(),'aireport','create','Create AI Report',true),
(gen_random_uuid(),'aireport','read','Read AI Report',true),
(gen_random_uuid(),'aireport','list','List AI Reports',true),
(gen_random_uuid(),'aireport','generate','Generate AI Report',true),
(gen_random_uuid(),'aireport','download','Download AI Report',true),
(gen_random_uuid(),'aireport','share','Share AI Report',true),
(gen_random_uuid(),'aireport','delete','Delete AI Report',true);