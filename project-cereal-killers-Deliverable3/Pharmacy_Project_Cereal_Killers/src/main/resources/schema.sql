CREATE TABLE IF NOT EXISTS patients (
    identificationNumber varchar(255) not null,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    address varchar(255),
    gender varchar(255),
    dateOfBirth varchar(255),
    languagePreference varchar(255),
    drugAllergies varchar(255),
    currentMedications varchar(255),
    insuranceNumber varchar(255),

    primary key (identificationNumber)
);

CREATE TABLE IF NOT EXISTS prescriptions (
    id uuid not null,
    patientID varchar(255) not null,
    prescriberID uuid not null,
    startDate date not null,
    endDate date not null,
    medicineDIN varchar(255) not null,
    medicineName  varchar(255),
    medicineStrength varchar(255),
    medicineAmount integer,
    methodOfAdministration varchar(255),
    frequencyOfAdministration varchar(255),
    optionalConsiderations varchar(255),
    refillable boolean,
    refillableCount integer,
    totalTimesPickedUp integer,
    authorizationFiled boolean,
    isVerified boolean,

    primary key (id)
    );

CREATE TABLE IF NOT EXISTS prescriptionFills (
    id uuid not null,
    patientID varchar(255) not null,
    prescriptionID uuid not null,
    status varchar(255),
    verified boolean,
    readyForPickup boolean,
    summary varchar(255),


    primary key (id)

);

CREATE TABLE IF NOT EXISTS medicines (
    drugIdentificationNum varchar(255) not null,
    name varchar(255) not null,
    stock integer,
    alternativeMedicine varchar(255),
    numberOfPrescriptionMade integer,

    primary key (drugIdentificationNum)
);

CREATE TABLE IF NOT EXISTS users (
     uid uuid not null,
     email varchar(255) not null,
     password varchar(255) not null,
     name varchar(255) not null,
     priority varchar(255) not null,
     isSignedIn boolean,
     role varchar(255),
     licenceNumber varchar(255),
     title varchar(255),
     address varchar(255),
     telephoneNumber varchar(255),

     primary key (uid)
);

alter table if exists prescriptions
    drop constraint if exists prescriptionPatient;

alter table if exists prescriptions
    drop constraint if exists prescriptionUser;

alter table if exists prescriptions
    drop constraint if exists prescriptionMedicine;

alter table if exists prescriptionFills
    drop constraint if exists prescriptionFillPrescription;

alter table if exists prescriptionFills
    drop constraint if exists prescriptionFillPatient;

alter table if exists prescriptions
    add constraint prescriptionPatient
        foreign key (patientID)
            references patients(identificationNumber);

alter table if exists prescriptions
    add constraint prescriptionUser
        foreign key (prescriberID)
            references users(uid);

alter table if exists prescriptions
    add constraint prescriptionMedicine
        foreign key (medicineDIN)
             references medicines(drugIdentificationNum);

alter table if exists prescriptionFills
    add constraint prescriptionFillPrescription
        foreign key (prescriptionID)
            references prescriptions(id);

alter table if exists prescriptionFills
    add constraint prescriptionFillPatient
        foreign key (patientID)
            references patients(identificationNumber);

INSERT INTO medicines (drugIdentificationNum, name, stock, alternativeMedicine, numberOfPrescriptionMade)
VALUES ('02023709','BALANCED SALT SOLUTION',100,'02008580',200),
       ('00786209','POTASSIUM CHLORIDE IN 0.9% SODIUM CHLORIDE INJECTION USP',140,'02023709',45),
       ('00786217','NERVE TEA',10,'02246134',56),
       ('02246134','0.4% LIDOCAINE HYDROCHLORIDE AND 5% DEXTROSE INJECTION',68,'00786217',46),
       ('00438022','DIURETIC TEA',89,'01263709',235),
       ('01263709','BALANCED SALT SOLUTION',34,'00438022',35),
       ('94859322','0.4% LIDOCAINE HYDROCHLORIDE AND 5% DEXTROSE INJECTION',56,'43544567',63),
       ('43544567','0.30% POTASSIUM CHLORIDE IN 5% DEXTROSE AND 0.45% SODIUM CHLORIDE INJECTION',35,'94859322',35),
       ('79765454','0.2% SODIUM FLUORIDE WEEKLY ORAL RINSE',64,'45668959',45),
       ('45668959','10% DEXTROSE AND 0.9% SODIUM CHLORIDE INJECTION USP',13,'79765454',23),
       ('43677990','1.1% NEUTRAL SODIUM FLUORIDE TOPICAL GEL',47,'34453779',45),
       ('34453779','1 STROKE ENVIRON',64,'43677990',456);

INSERT INTO users (uid, email, password, name, priority, isSignedIn,role,licenceNumber,title,address,telephoneNumber)
VALUES
    ('11111111-1111-1111-1111-111111111111','ad@ad.ad','000000','Admin','1',false,'Admin',null,null,null,null),
    ('22222222-2222-2222-2222-222222222222','pr@pr.pr','000000','Prescriber','3',false,'Prescriber',12345678,null,null,null),
    ('33333333-3333-3333-3333-333333333333','ph@ph.ph','000000','Pharmacist','2',false,'Pharmacist',null,null,null,null),
    ('44444444-4444-4444-4444-444444444444','a@a.a','000000','a','3',false,'Prescriber',null,null,null,null),
    ('55555555-5555-5555-5555-555555555555','b@b.b','000000','b','2',false,'Pharmacist',null,null,null,null);

INSERT INTO patients (identificationNumber, firstName, lastName, address, gender, dateOfBirth,languagePreference,drugAllergies,currentMedications,insuranceNumber)
VALUES
    ('11111','a','b','a street','Male','2000/12/02','English',null,null,'123'),
    ('22222','b','c','b street','Female','1998/02/02','English',null,null,'234'),
    ('33333','c','d','c street','Male','2000/12/02','English',null,null,'352');

INSERT INTO prescriptions (id, patientID, prescriberID, startDate, endDate, medicineDIN,medicineName,medicineStrength,medicineAmount,methodOfAdministration,frequencyOfAdministration,
                           optionalConsiderations,refillable,refillableCount,totalTimesPickedUp,authorizationFiled,isVerified)
VALUES
    ('66666666-6666-6666-6666-666666666666','11111','22222222-2222-2222-2222-222222222222','2022/09/08','2022/12/08','02023709','BALANCED SALT SOLUTION',null,2,null,'twice a day',
     null,false,3,0,false,false),
    ('77777777-7777-7777-7777-777777777777','22222','44444444-4444-4444-4444-444444444444','2022/10/08','2023/1/08','02023709','BALANCED SALT SOLUTION',null,4,null,'once a day',
     null,false,3,0,false,false);