package co.chen.form;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import co.chen.bean.Client;
import eu.medsea.mimeutil.MimeUtil;


public class CreationClientForm {

    /* Field names constants */
    private static final String FIELD_name      = "name";
    private static final String FIELD_surname   = "surname";
    private static final String FIELD_city      = "city";
    private static final String FIELD_postcode  = "postcode";
    private static final String FIELD_address   = "address";
    private static final String FIELD_phone     = "phone";
    private static final String FIELD_mail      = "mail";
    private static final String FIELD_passwd    = "passwd";
    private static final String FIELD_passwd_confirm = "passwdconfirm";
    private static final String FIELD_image = "image";

    private Boolean shouldCreateFile = false;

    // private String result;
    private Map<String, String> errors = new HashMap<String, String>();

    public Client validate( HttpServletRequest request ) {
        String name = getFieldValue(request, FIELD_name);
        String surname = getFieldValue(request, FIELD_surname);
        String city = getFieldValue(request, FIELD_city);
        String postcode = getFieldValue(request, FIELD_postcode);
        String address = getFieldValue(request, FIELD_address);
        String phone = getFieldValue(request, FIELD_phone);
        String mail = getFieldValue(request, FIELD_mail);
        String passwd = getFieldValue(request, FIELD_passwd);
        String passwd_confirm = getFieldValue(request, FIELD_passwd_confirm);

        Client client = new Client();

        /* Validate field <name> */
        try {
            validateName(name);
        } catch ( Exception e ) {
            name = "";
            errors.put(FIELD_name, e.getMessage());
        }
        client.setName(name);

        /* Validate field <surname> */
        try {
            validateSurname(surname);
        } catch ( Exception e ) {
            surname = "";
            errors.put(FIELD_surname, e.getMessage());
        }
        client.setSurname(surname);

        /* Validate field <city> */
        try {
            validateCity(city);
        } catch ( Exception e ) {
            city = "";
            errors.put(FIELD_city, e.getMessage());
        }
        client.setCity(city);

        /* Validate field <postcode> */
        try {
            validatePostcode(postcode);
        } catch ( Exception e ) {
            postcode = "";
            errors.put(FIELD_postcode, e.getMessage());
        }
        client.setPostcode(postcode);

        /* Validate field <address> */
        try {
            validateAddress(address);
        } catch ( Exception e ) {
            address = "";
            errors.put(FIELD_address, e.getMessage());
        }
        client.setAddress(address);

        /* Validate field <phone> */
        try {
            validatePhone(phone);
        } catch ( Exception e ) {
            phone = "";
            errors.put(FIELD_phone, e.getMessage());
        }
        client.setPhone(phone);

        /* Validate field <mail> */
        try {
            validateMail(mail);
        } catch ( Exception e ) {
            mail = "";
            errors.put(FIELD_mail, e.getMessage());
        }
        client.setMail(mail);

        /* Validate field <passwd> and <passwd_confirm> */
        try {
            validatePasswordConfirm(passwd, passwd_confirm);
        } catch (Exception e) {
            errors.put(FIELD_passwd_confirm, e.getMessage());
        }
        try {
            validatePassword(passwd);
        } catch ( Exception e ) {
            passwd = "";
            errors.put(FIELD_passwd, e.getMessage());
        }
        client.setPasswd(passwd);

        /* Validate user uploaded image */
        try {
            validateImage(request);
        } catch (Exception e) {
            System.err.println(e);
            errors.put(FIELD_image, e.getMessage());
        }

        return client;
    }

    /* Validators */
    private void validateName( String name ) throws Exception {
        if ( name == null ) {
            throw new Exception("Pas de nom fourni");
        } else if ( name.length() < 2 ) {
            throw new Exception("Le nom doit contenir au moins deux caractères");
        } else if ( !name.matches("^[^0-9]+$") ) {
            throw new Exception("Le nom ne doit contenir que des lettres");
        }
    }
    private void validateSurname( String surname ) throws Exception {
        if (surname == null) {
            throw new Exception("Pas de prénom fourni");
        } else if (surname.length() < 2) {
            throw new Exception("Le prénom doit contenir au moins deux caractères");
        } else if (!surname.matches("^[^0-9]+$")) {
            throw new Exception("Le prénom ne doit contenir que des lettres");
        }
    }
    private void validateCity( String city ) throws Exception {
        if (city == null) {
            throw new Exception("Pas de ville fournie");
        } else if (city.length() < 2) {
            throw new Exception("Le nom de la ville doit contenir au moins deux caractères");
        } else if (!city.matches("^[^0-9]+$")) {
            throw new Exception("Le nom de la ville ne doit contenir que des lettres");
        }
    }
    private void validatePostcode( String postcode ) throws Exception {
        if (postcode == null) {
            throw new Exception("Le code postal doit être précisé");
        } else if ( !postcode.matches("^[0-9]{5}$") ) {
            throw new Exception("Le code postal doit être composé de cinq chiffres");
        }
    }
    private void validateAddress( String address ) throws Exception {
        if ( address == null ) {
            throw new Exception("Une addresse doit être fournie");
        } else if ( address.length() < 5 ) {
            throw new Exception("L'adresse doit faire au moins cinq caractères");
        }
    }
    private void validatePhone( String phone ) throws Exception {

    }
    private void validateMail( String mail ) throws Exception {
        if ( mail == null ) {
            throw new Exception("Une addresse mail doit être fournie");
        } else if ( !mail.matches("^\\S+@\\S+\\.\\S+$") ) {
            throw new Exception("L'adresse mail n'est pas valide");
        }
    }
    private void validatePassword( String passwd ) throws Exception {
        if ( passwd == null ) {
            throw new Exception("Un mot de passe doit être précisé");
        } else if ( passwd.length() < 8  || passwd.length() > 32 ) {
            throw new Exception("Le mot de passe doit faire entre 8 et 32 caractères");
        } else if ( !passwd.matches(".*\\d.*") ) {
            throw new Exception("Le mot de passe doit contenir au moins un chiffre");
        } else if ( !passwd.matches(".*[^A-Za-z\\d ].*") ) {
            throw new Exception("Le mot de passe doit contenir au moins un caractère spécial");
        }
    }

    private void validatePasswordConfirm( String passwd, String passwd_confirm ) throws Exception {
        if ( passwd_confirm == null ) {
            throw new Exception("Veuillez confirmer le mot de passe");
        } else if ( !passwd.equals(passwd_confirm) ) {
            System.out.println("'" + passwd + "' '" + passwd_confirm + "'");
            throw new Exception("Les mots de passe de correspondent pas");
        }
    }

    private void validateImage( HttpServletRequest request) throws Exception {

        String filename = null;
        BufferedInputStream data = null;

        try {
            Part part = request.getPart( FIELD_image );
            filename = getFilename( part );

            if ( filename != null && !filename.isEmpty() ) {
                /* Récupération du contenu du fichier */
                data = new BufferedInputStream( part.getInputStream() );

                /* Extraction du type MIME du fichier depuis l'InputStream */
                MimeUtil.registerMimeDetector( "eu.medsea.mimeutil.detector.MagicMimeMimeDetector" );
                Collection<?> mimeTypes = MimeUtil.getMimeTypes( data );
                
                if ( !mimeTypes.toString().startsWith("image/png") ) {
                    throw new Exception( "Le fichier envoyé doit être une image PNG" );
                }

                this.shouldCreateFile = true;
            }

        } catch (IllegalStateException e) {
            throw new Exception("Le fichier ne doit pas dépasser 1Mo");
        } catch (IOException e) {
            throw new Exception("Erreur de configuration du serveur");
        } catch (ServletException e) {
            throw new Exception("Type de requête non supporté");
        }
    }

    private static String getFieldValue( HttpServletRequest request, String fieldName ) {
        String val = null;

        try {
            val = getValue(request.getPart(fieldName));
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            System.out.println(fieldName + "= " + val);
        }

        if ( val == null || val.trim().length() == 0 ) {
            return null;
        } else {
            return val;
        }
    }

    private static String getValue(Part part) throws IOException {
        BufferedReader reader = new BufferedReader( new InputStreamReader( part.getInputStream(), "UTF-8"));
        StringBuilder value = new StringBuilder();

        char[] buffer = new char[1024];
        int length = 0;

        while ( (length = reader.read(buffer)) > 0 ) {
            value.append(buffer, 0, length);
            System.out.println("Read: " + buffer);
        }

        return value.toString();
    }

    private static String getFilename(Part part) {
        for ( String contentDisposition : part.getHeader("content-disposition").split(";") ) {
            if ( contentDisposition.trim().startsWith("filename") ) {
                return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }

    /* Getters & Setters */
    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public Boolean getShouldCreateFile() {
        return shouldCreateFile;
    }

    public void setShouldCreateFile(Boolean shouldCreateFile) {
        this.shouldCreateFile = shouldCreateFile;
    }

    public Boolean shouldCreateFile() {
        return this.getShouldCreateFile();
    }
}
