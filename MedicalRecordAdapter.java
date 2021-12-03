import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MedicalRecordAdapter implements MedicalRecord {
    private HealthRecord record;

    public MedicalRecordAdapter(HealthRecord record) {
        this.record = record;
    }

    /**
     * gets the full name and then only return the first name
     * @return firstName
     */
    public String getFirstName() {
        String Name = record.getName();
        String[] firstLast = Name.split(" ");
        String firstName = firstLast[0];
        return firstName;
    }

    /**
     * get the full name then retruns the last name
     * @retrun lastName
     */
    public String getLastName() {
        String Name = record.getName();
        String[] firstLast = Name.split(" ");
        String lastName = firstLast[1];
        return lastName;
    }

    /**
     * Returns the Birthdate
     * @return birthdate
     */
    public Date getBirthday() {
        return record.getBirthdate();
    }

    /**
     * Returns the correct type Gender basted on the string
     * @return gender
     */
    public Gender getGender() {
        Gender gender;
        if(record.getGender().equalsIgnoreCase("male")) {
            gender = Gender.MALE;
            return gender;
        } else if(record.getGender().equalsIgnoreCase("female")) {
            gender = Gender.FEMALE;
            return gender;
        } else {
            gender = Gender.OTHER;
            return gender;
        }
    }

    /**
     * Adds a visit to the array of visit history
     * @param date: Date
     * @peram well: Boolean
     * @peram description: String
     */
    public void addVisit(Date date, boolean well, String description) {
        record.addHistory(date, well, description);
    }

    /**
     * Converst the visit style in the Carolina Health Record to and SC Medical Record
     * @return history
     */
    public ArrayList<Visit> getVisitHistory() {
        ArrayList<Visit> history = new ArrayList<Visit>();
        ArrayList<String> stringHistory = new ArrayList<>();
        stringHistory = record.getHistory();
        for(int i = 0; i < record.getHistory().size(); i++) {
            String aHistory = stringHistory.get(i);
            String[] lines = aHistory.split(System.lineSeparator());
            String[] line1 = lines[0].split(" ");
            String[] line2 = lines[1].split(" ");
            String[] line3 = lines[2].split(" ");
            String dateString = line1[1]; 
            dateString += line1[2];
            dateString += line1[3];
            dateString += line1[4];
            String[] dateValues = dateString.split("/");
            String wellString = line2[1];
            String description = line3[1];
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E dd, MM, yyyy");
            //Date date = makeDate(dateValues[0],dateValues[1],dateValues[2]);
            //Date date = new SimpleDateFormat("E dd MM yyyy").parse(dateString);
            Date date = new Date(2004, 7, 8);
            Boolean well = true;
            if(wellString.equalsIgnoreCase("false")) {
                well = false;
            }
            history.add(new Visit(date, well, description));
        }
        return history;
    }

    /**
     * Assigns the users medical record to a string
     * @return String
     */
    public String toString() {
        String result = "***** " + this.getFirstName() + " " + this.getLastName() + " *****\n";
        result += "Birthday: " + this.getBirthday() + "\n";
        result += "Gender: " + this.getGender() + "\n";
        result += "Medical Visit History: \n";

        if(getVisitHistory().size() == 0){
            result += "No visits yet";
        } else {
            for(Visit visit : getVisitHistory()){
                result += visit.toString() + "\n";
            }
        }

        return result;
    }
}
