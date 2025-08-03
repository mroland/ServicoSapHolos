package br.com.atarde.servicosap.util;

import com.sap.smb.sbo.api.ICompany;
import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOCOMUtil;
import com.sap.smb.sbo.api.SBOErrorMessage;

import br.com.atarde.servicosap.sap.model.Empresa;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class ConexaoSapUtil {

    private ICompany oCompany;
    private static ConexaoSapUtil conexaoSapUtil;

    public static ConexaoSapUtil getConnection(Empresa empresa) throws TSApplicationException {


        if (conexaoSapUtil == null) {

            conexaoSapUtil = new ConexaoSapUtil();

            conexaoSapUtil.conexao(empresa);

        } else {

            if (!conexaoSapUtil.isConnected()) {

                conexaoSapUtil.conexao(empresa);

            }

        }

        return conexaoSapUtil;

    }

    
    public static ConexaoSapUtil getConnection() throws TSApplicationException {

        if (conexaoSapUtil == null) {

            conexaoSapUtil = new ConexaoSapUtil();

            conexaoSapUtil.conexao();

        } else {

            if (!conexaoSapUtil.isConnected()) {

                conexaoSapUtil.conexao();

            }

        }

        return conexaoSapUtil;

    }
    private void conexao() {
		// TODO Auto-generated method stub
		
	}


	private ConexaoSapUtil() {

    }

    private void conexao(Empresa empresa) throws TSApplicationException {

        SBOErrorMessage errorMessage;

        if (TSUtil.isEmpty(this.oCompany)) {

            oCompany = SBOCOMUtil.newCompany();

        }

        oCompany.setServer(empresa.getServidor());
        oCompany.setDbUserName(empresa.getDbUsuario());
        oCompany.setDbPassword(empresa.getDbSenha());
        oCompany.setCompanyDB(empresa.getDbInstancia());
        oCompany.setUserName(empresa.getAppUsuario());
        oCompany.setPassword(empresa.getAppSenha());
        //oCompany.setLanguage(SBOCOMConstants.BoSuppLangs_ln_Portuguese_Br);
        
        oCompany.setLanguage(SBOCOMConstants.BoSuppLangs_ln_Portuguese_Br);
        oCompany.setUseTrusted(false);
        oCompany.setDbServerType(SBOCOMConstants.BoDataServerTypes_dst_MSSQL2008);
        oCompany.setLicenseServer(empresa.getServidorLicenca().concat(":").concat(empresa.getPortaLicenca()));

        int retorno = oCompany.connect();

        if (retorno != 0) {
            errorMessage = oCompany.getLastError();
            System.err.println("Error "
                    + errorMessage.getErrorCode()
                    + " : "
                    + errorMessage.getErrorMessage());

            throw new TSApplicationException("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());
        } else {
            //System.out.println("Conectado");
        }
    }

    public void disconnect() {

        oCompany.disconnect();

        oCompany.release();

    }

    public ICompany getCompany() {
        return oCompany;
    }

    public boolean isConnected() {

        Boolean estaConectado = oCompany.isConnected();

        return estaConectado.booleanValue();

    }
}
