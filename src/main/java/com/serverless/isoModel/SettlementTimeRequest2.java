
package com.serverless.isoModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SettlementTimeRequest2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SettlementTimeRequest2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CLSTm" type="{urn:iso:std:iso:20022:tech:xsd:pacs.008.001.07}ISOTime" minOccurs="0"/>
 *         &lt;element name="TillTm" type="{urn:iso:std:iso:20022:tech:xsd:pacs.008.001.07}ISOTime" minOccurs="0"/>
 *         &lt;element name="FrTm" type="{urn:iso:std:iso:20022:tech:xsd:pacs.008.001.07}ISOTime" minOccurs="0"/>
 *         &lt;element name="RjctTm" type="{urn:iso:std:iso:20022:tech:xsd:pacs.008.001.07}ISOTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SettlementTimeRequest2", namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.07", propOrder = {
    "clsTm",
    "tillTm",
    "frTm",
    "rjctTm"
})
public class SettlementTimeRequest2 {

    @XmlElement(name = "CLSTm", namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.07")
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar clsTm;
    @XmlElement(name = "TillTm", namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.07")
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar tillTm;
    @XmlElement(name = "FrTm", namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.07")
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar frTm;
    @XmlElement(name = "RjctTm", namespace = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.07")
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar rjctTm;

    /**
     * Gets the value of the clsTm property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCLSTm() {
        return clsTm;
    }

    /**
     * Sets the value of the clsTm property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCLSTm(XMLGregorianCalendar value) {
        this.clsTm = value;
    }

    /**
     * Gets the value of the tillTm property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTillTm() {
        return tillTm;
    }

    /**
     * Sets the value of the tillTm property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTillTm(XMLGregorianCalendar value) {
        this.tillTm = value;
    }

    /**
     * Gets the value of the frTm property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFrTm() {
        return frTm;
    }

    /**
     * Sets the value of the frTm property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFrTm(XMLGregorianCalendar value) {
        this.frTm = value;
    }

    /**
     * Gets the value of the rjctTm property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRjctTm() {
        return rjctTm;
    }

    /**
     * Sets the value of the rjctTm property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRjctTm(XMLGregorianCalendar value) {
        this.rjctTm = value;
    }

}