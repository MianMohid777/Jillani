package Assignment.Model.Service;

import Assignment.Model.Entity.Applicant;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public interface Strategy {

    HashMap<Integer, List<Applicant>> matchingStrategy();
}
