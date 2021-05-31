package javaCamp.core.utilities;

import javaCamp.core.utilities.results.Result;
import javaCamp.core.utilities.results.SuccessResult;

public class BusinessEngine {

    public static Result run(Result... logics) {
        for (Result logic : logics) {
            if (!logic.isSuccess()){
                return logic;
            }
        }
        return new SuccessResult();
    }
}
