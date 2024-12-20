package br.com.atarde.servicosap.util;

import java.text.Normalizer;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.com.topsys.util.TSCryptoUtil;
import br.com.topsys.util.TSUtil;

public final class Utilitarios {

	private Utilitarios() {

	}

	public static String tratarString(String valor) {
		if (!TSUtil.isEmpty(valor)) {
			valor = valor.trim().replaceAll("  ", " ");
		}

		return valor;
	}

	public static Long tratarLong(Long valor) {
		if ((!TSUtil.isEmpty(valor)) && (valor.equals(Long.valueOf(0L)))) {
			valor = null;
		}

		return valor;
	}

	public static String removerAcentos(String palavra) {
		if (TSUtil.isEmpty(palavra)) {
			return null;
		}

		return Normalizer.normalize(new StringBuilder(palavra), Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}

	public static String gerarHash(String valor) {
		if (!TSUtil.isEmpty(valor)) {
			valor = TSCryptoUtil.gerarHash(valor, "md5");
		}

		return valor;
	}

	public static String gerarSenha() {
		Calendar rightNow = Calendar.getInstance();

		int diaAtual = rightNow.get(5);
		int mesAtual = rightNow.get(2) + 1;
		int anoAtual = rightNow.get(1);
		int horaAtual = rightNow.get(11);
		int minutoAtual = rightNow.get(12);
		int segAtual = rightNow.get(13);
		int miliAtual = rightNow.get(14);

		String senha = anoAtual + mesAtual + diaAtual + horaAtual + minutoAtual + segAtual + miliAtual + "";

		return senha;
	}

	public static String getSituacao(Boolean flagAtivo) {
		if ((!TSUtil.isEmpty(flagAtivo)) && (flagAtivo.booleanValue())) {
			return "Ativo";
		}

		return "Inativo";
	}

	public static String getDiaMesAno(Date data) {

		if (!TSUtil.isEmpty(data)) {
			Calendar calendar = Calendar.getInstance(Locale.getDefault());
			calendar.setTime(data);
			int dia = calendar.get(Calendar.DAY_OF_MONTH);
			int mes = calendar.get(Calendar.MONTH);
			mes = mes + 1;
			String diaMesAno = "" + dia;
			if (mes < 10) {
				diaMesAno = diaMesAno + "0";
			}
			diaMesAno = diaMesAno + "_" + mes + "_" + calendar.get(Calendar.YEAR) + "_";
			return diaMesAno;

		}
		return null;
	}

}