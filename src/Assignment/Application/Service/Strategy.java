package Assignment.Application.Service;

import Assignment.Application.Model.Applicant;

import java.util.HashMap;
import java.util.List;

public interface Strategy {

    HashMap<String, List<Applicant>> matchingStrategy();
}
