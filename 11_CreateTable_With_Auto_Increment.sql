CREATE TABLE "Students"
(    
   "ID" INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),   
   "NAME" VARCHAR(50),     
   "SALLERY" DOUBLE,
   "BD" DATE
);