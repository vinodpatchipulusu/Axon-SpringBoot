create table TRANSFER_TRANSACTION (
id number primary key, 
source_account_id number, 
destination_account_id number, 
reference_number varchar2(200),  
transaction_id number,
transaction_status varchar2(200),
transaction_amt number,
transfer_date date,
transfer_type varchar2(200)
);
  
create sequence transfer_transaction_seq;