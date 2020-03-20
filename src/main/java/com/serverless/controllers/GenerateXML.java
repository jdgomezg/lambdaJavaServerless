package com.serverless.controllers;

import com.serverless.isoModel.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

public class GenerateXML {
    public ByteArrayOutputStream generarIso() throws JAXBException, DatatypeConfigurationException, IOException {
        Document doc = new ObjectFactory().createDocument();

        doc.setFIToFICstmrCdtTrf(new FIToFICustomerCreditTransferV07());
        //Datos del header
        doc.getFIToFICstmrCdtTrf().setGrpHdr(new GroupHeader70());
        doc.getFIToFICstmrCdtTrf().getGrpHdr().setMsgId("BATLANTI18101100000004");

        GregorianCalendar cal = new GregorianCalendar();
        XMLGregorianCalendar xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        doc.getFIToFICstmrCdtTrf().getGrpHdr().setCreDtTm(xmlCalendar);

        doc.getFIToFICstmrCdtTrf().getGrpHdr().setNbOfTxs("2");

        ActiveCurrencyAndAmount activeCurrencyAndAmount = new ActiveCurrencyAndAmount();
        activeCurrencyAndAmount.setCcy("USD");
        activeCurrencyAndAmount.setValue(new BigDecimal(600.00));

        doc.getFIToFICstmrCdtTrf().getGrpHdr().setTtlIntrBkSttlmAmt(activeCurrencyAndAmount);

        doc.getFIToFICstmrCdtTrf().getGrpHdr().setIntrBkSttlmDt(xmlCalendar);

        SettlementInstruction4 settlementInstruction4 = new SettlementInstruction4();
        settlementInstruction4.setSttlmMtd(SettlementMethod1Code.CLRG);
        ClearingSystemIdentification3Choice clearingSystemIdentification3Choice = new ClearingSystemIdentification3Choice();
        clearingSystemIdentification3Choice.setPrtry("ACH");
        settlementInstruction4.setClrSys(clearingSystemIdentification3Choice);
        doc.getFIToFICstmrCdtTrf().getGrpHdr().setSttlmInf(settlementInstruction4);

        BranchAndFinancialInstitutionIdentification5 branchAndFinancialInstitutionIdentification5 = new BranchAndFinancialInstitutionIdentification5();
        FinancialInstitutionIdentification8 financialInstitutionIdentification8 = new FinancialInstitutionIdentification8();
        financialInstitutionIdentification8.setBICFI("BATLANTI");
        branchAndFinancialInstitutionIdentification5.setFinInstnId(financialInstitutionIdentification8);
        doc.getFIToFICstmrCdtTrf().getGrpHdr().setInstdAgt(branchAndFinancialInstitutionIdentification5);

        //Datos Transaccion
        CreditTransferTransaction30 creditTransferTransaction30 = new CreditTransferTransaction30();
        int number_cicle = Integer.parseInt(System.getenv("NUMBER_CICLE"));
        for (int i = 0; i < number_cicle; i++) {

            PaymentIdentification3 paymentIdentification3 = new PaymentIdentification3();
            paymentIdentification3.setInstrId("0005006008-00900010401");
            paymentIdentification3.setEndToEndId("999999401");
            paymentIdentification3.setTxId("1810110000401");
            creditTransferTransaction30.setPmtId(paymentIdentification3);

            PaymentTypeInformation21 paymentTypeInformation21 = new PaymentTypeInformation21();
            ServiceLevel8Choice serviceLevel8Choice = new ServiceLevel8Choice();
            serviceLevel8Choice.setCd("SEPA");
            paymentTypeInformation21.setSvcLvl(serviceLevel8Choice);
            LocalInstrument2Choice localInstrument2Choice = new LocalInstrument2Choice();
            localInstrument2Choice.setPrtry("B2B");
            paymentTypeInformation21.setLclInstrm(localInstrument2Choice);
            CategoryPurpose1Choice categoryPurpose1Choice = new CategoryPurpose1Choice();
            categoryPurpose1Choice.setCd("CASH");
            paymentTypeInformation21.setCtgyPurp(categoryPurpose1Choice);
            creditTransferTransaction30.setPmtTpInf(paymentTypeInformation21);

            ActiveCurrencyAndAmount activeCurrencyAndAmount1 = new ActiveCurrencyAndAmount();
            activeCurrencyAndAmount.setCcy("USD");
            activeCurrencyAndAmount.setValue(new BigDecimal(200.00));
            creditTransferTransaction30.setIntrBkSttlmAmt(activeCurrencyAndAmount);

            creditTransferTransaction30.setChrgBr(ChargeBearerType1Code.SLEV);

            PartyIdentification125 partyIdentification125 = new PartyIdentification125();
            partyIdentification125.setNm("Fabricio Zapata");
            Party34Choice party34Choice = new Party34Choice();
            PersonIdentification13 personIdentification13 = new PersonIdentification13();
            GenericPersonIdentification1 genericPersonIdentification1 = new GenericPersonIdentification1();
            genericPersonIdentification1.setId("06018234539401");
            personIdentification13.getOthr().add(genericPersonIdentification1);
            party34Choice.setPrvtId(personIdentification13);
            partyIdentification125.setId(party34Choice);
            partyIdentification125.setCtryOfRes("PA");
            creditTransferTransaction30.setDbtr(partyIdentification125);

            CashAccount24 cashAccount24 = new CashAccount24();
            AccountIdentification4Choice accountIdentification4Choice = new AccountIdentification4Choice();
            GenericAccountIdentification1 genericAccountIdentification1 = new GenericAccountIdentification1();
            genericAccountIdentification1.setId("0005006008401");
            accountIdentification4Choice.setOthr(genericAccountIdentification1);
            cashAccount24.setId(accountIdentification4Choice);
            creditTransferTransaction30.setDbtrAcct(cashAccount24);

            BranchAndFinancialInstitutionIdentification5 branchAndFinancialInstitutionIdentification51 = new BranchAndFinancialInstitutionIdentification5();
            FinancialInstitutionIdentification8 financialInstitutionIdentification81 = new FinancialInstitutionIdentification8();
            financialInstitutionIdentification81.setBICFI("BATLANTI");
            branchAndFinancialInstitutionIdentification51.setFinInstnId(financialInstitutionIdentification81);
            creditTransferTransaction30.setDbtrAgt(branchAndFinancialInstitutionIdentification51);

            BranchAndFinancialInstitutionIdentification5 branchAndFinancialInstitutionIdentification52 = new BranchAndFinancialInstitutionIdentification5();
            FinancialInstitutionIdentification8 financialInstitutionIdentification82 = new FinancialInstitutionIdentification8();
            financialInstitutionIdentification82.setBICFI("BCARIBBE");
            branchAndFinancialInstitutionIdentification52.setFinInstnId(financialInstitutionIdentification82);
            creditTransferTransaction30.setCdtrAgt(branchAndFinancialInstitutionIdentification52);

            PartyIdentification125 partyIdentification1251 = new PartyIdentification125();
            partyIdentification1251.setNm("Fabricio Zapata");
            Party34Choice party34Choice1 = new Party34Choice();
            PersonIdentification13 personIdentification131 = new PersonIdentification13();
            GenericPersonIdentification1 genericPersonIdentification11 = new GenericPersonIdentification1();
            genericPersonIdentification11.setId("5009956234401");
            personIdentification131.getOthr().add(genericPersonIdentification1);
            party34Choice1.setPrvtId(personIdentification13);
            partyIdentification125.setId(party34Choice);
            partyIdentification125.setCtryOfRes("EC");
            creditTransferTransaction30.setCdtr(partyIdentification125);

            CashAccount24 cashAccount241 = new CashAccount24();
            AccountIdentification4Choice accountIdentification4Choice1 = new AccountIdentification4Choice();
            GenericAccountIdentification1 genericAccountIdentification11 = new GenericAccountIdentification1();
            genericAccountIdentification11.setId("5004234592401");
            accountIdentification4Choice1.setOthr(genericAccountIdentification11);
            cashAccount241.setId(accountIdentification4Choice);
            creditTransferTransaction30.setCdtrAcct(cashAccount241);

            Purpose2Choice purpose2Choice = new Purpose2Choice();
            purpose2Choice.setCd("ADVA");
            creditTransferTransaction30.setPurp(purpose2Choice);

            RemittanceInformation15 remittanceInformation15 = new RemittanceInformation15();
            remittanceInformation15.getUstrd().add("Informacion adicional del pago " + i);
            creditTransferTransaction30.setRmtInf(remittanceInformation15);

            doc.getFIToFICstmrCdtTrf().getCdtTrfTxInf().add(creditTransferTransaction30);
        }

        // create JAXB context and instantiate marshaller
        JAXBContext context = JAXBContext.newInstance(Document.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        JAXBElement<Document> jaxbElement = new ObjectFactory().createDocument(doc);

        ByteArrayOutputStream fileOutput = new ByteArrayOutputStream();
        // Write file
        m.marshal(jaxbElement, fileOutput);

        return fileOutput;
    }

}
