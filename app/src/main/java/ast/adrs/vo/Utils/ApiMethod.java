package ast.adrs.vo.Utils;


public interface ApiMethod {


    interface GET {


    }

    interface POST {
        String signIn = "login";
        String GetDiseaseExecutiveSummary = "DiseaseIntimation/GetDiseaseExecutiveSummary";
    }


    interface Patch {
        String forgotPassword = "forgot";
        String verifyCode = "verifyCode";
        String resetPassword = "resetPassword";
    }

    interface HEADER {
        String Authorization = "Authorization";
        String Authorization_TOKEN = "token";
        String Bearer = "Bearer ";
        String Lang = "lang";
        String Default_Auth = "mankoosha!and$";
    }

}

