package musicshow;

    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

//Εισάγουμε τις απαραίτητες κλάσεις

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import musicshow.contestants.Band;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

    /**
     * @author user
     */

// Να δημιουργήσετε κλάση ReportShow, η οποία θα αποθηκεύει στο αρχείο outputShow.xml τα επεισόδια που διαδραματίστηκαν
    public class ReportShow {

        //Μέθοδος η οποία αποθηκεύει στο αρχείο outputShow.xml τα αποτελέσματα από τα επεισόδια που διαδραματίστηκαν
        public static void CreateXml(MusicShow m) {

            try {
                //Δημιουργεία αντικειμένων
                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                Document document = documentBuilder.newDocument();

                // H SimpleDateFormat είναι μια κλάση για τη μορφοποίηση ημερομηνιών
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");


                DecimalFormat decimalFormat = new DecimalFormat("#.00");


                // root element ΕΚΠΟΜΠΗ
                Element root = document.createElement("ΕΚΠΟΜΠΗ");
                document.appendChild(root);


                for (int i = 0; i < m.getEpisodes().size(); i++) {


                    //ΕΠΕΙΣΟΔΙΟ
                    Element episodio = document.createElement("ΕΠΕΙΣOΔΙΟ");
                    root.appendChild(episodio);

                    //ΑΡΙΘΜΟΣ_ΕΠΕΙΣΟΔΙΟΥ
                    Element number = document.createElement("ΑΡΙΘΜΟΣ_ΕΠΕΙΣΟΔΙΟΥ");
                    number.appendChild(document.createTextNode(m.getEpisodes().get(i).getNum().toString()));
                    episodio.appendChild(number);

                    //ΗΜΕΡΟΜΗΝΙΑ_ΕΠΕΙΣΟΔΙΟΥ
                    Element date = document.createElement("ΗΜΕΡΟΜΗΝΙΑ_ΕΠΕΙΣΟΔΙΟΥ");
                    Date episod = m.getEpisodes().get(i).getDate();
                    String dateEpisod = simpleDateFormat.format(episod);
                    date.appendChild(document.createTextNode(dateEpisod));
                    episodio.appendChild(date);

                    //ΔΙΑΡΚΕΙΑ_ΕΠΕΙΣΟΔΙΟΥ
                    Element duration = document.createElement("ΔΙΑΡΚΕΙΑ_ΕΠΕΙΣΟΔΙΟΥ");
                    duration.appendChild(document.createTextNode(m.getEpisodes().get(i).getDurationMin().toString()));
                    episodio.appendChild(duration);

                    //Κατάταξη διαγωνιζομένων κατά φθίνουσα συγκεντρωτική βαθμολογία
                    //ΚΑΤΑΤΑΞΗ_ΔΙΑΓΩΝΙΖΟΜΕΝΩΝ
                    Element contestantPlace = document.createElement("ΚΑΤΑΤΑΞΗ_ΔΙΑΓΩΝΙΖΟΜΕΝΩΝ");
                    episodio.appendChild(contestantPlace);

                    for (int j = 0; j < m.getEpisodes().get(i).getContestants().size(); j++) {

                        //ΚΑΤΗΓΟΡΙΑ
                        Element katig = document.createElement("ΚΑΤΗΓΟΡΙΑ");
                        katig.appendChild(document.createTextNode(m.getEpisodes().get(i).getContestants().get(j).getCategory()));
                        contestantPlace.appendChild(katig);

                        //ONOMA
                        Element name = document.createElement("ONOMA");
                        name.appendChild(document.createTextNode(m.getEpisodes().get(i).getContestants().get(j).getName()));
                        contestantPlace.appendChild(name);

                        //ΒΑΘΜΟΛΟΓΙΑ
                        Element sgrade = document.createElement("ΒΑΘΜΟΛΟΓΙΑ");
                        String v = decimalFormat.format(m.getEpisodes().get(i).getContestants().get(j).getSum());
                        sgrade.appendChild(document.createTextNode(v));
                        contestantPlace.appendChild(sgrade);


                        //ΗΜΕΡΟΜΗΝΙΑ_ΙΔΡΥΣΗΣ Συγκροτήματος
                        if (m.getEpisodes().get(i).getContestants().get(j) instanceof Band) {
                            Element createDate = document.createElement("ΗΜΕΡΟΜΗΝΙΑ_ΙΔΡΥΣΗΣ");
                            Date band = m.getEpisodes().get(i).getContestants().get(j).getFormationDate();
                            String dateBand = simpleDateFormat.format(band);
                            createDate.appendChild(document.createTextNode(dateBand));
                            contestantPlace.appendChild(createDate);
                        }
                    }

                }

                //NIKHTEΣ
                //Ο πτώτος νικητής
                Element winner = document.createElement("ΝΙΚΗΤΗΣ");
                winner.appendChild(document.createTextNode(m.getEpisode(9).getContestants().get(0).getName()));
                root.appendChild(winner);

                //Ο δεύτερος.
                Element second = document.createElement("ΔΕΥΤΕΡΟΣ");
                second.appendChild(document.createTextNode(m.getEpisode(9).getContestants().get(1).getName()));
                root.appendChild(second);

                // Δημιουργία του XML Αρχείου
                // Μετατροπή του DOM Αντικειμένου σε ένα XML Αρχείο
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(new File("C:\\Users\\theo\\Desktop\\EAΠ εργασίες 2018-2019\\ΠΛΗ 24\\ergasia2\\ReportShow.xml"));
                transformer.transform(domSource, streamResult);
                System.out.println("Tο αρχείο έχει αποθηκευτεί στο C:\\Users\\theo\\Desktop\\EAΠ εργασίες 2018-2019\\ΠΛΗ 24\\ergasia2\\ReportShow.xml");

            } catch (ParserConfigurationException pce) {
                pce.printStackTrace();
            } catch (TransformerException tfe) {
                tfe.printStackTrace();
            }
        }

    }



