package br.com.ilegra.spring.analyse.dat.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

import java.io.*;
import java.util.*;

public final class ArquivoUtil {

    public static final String SEPARADOR_DEFAULT = "ç";

    private ArquivoUtil() {
    }

    public static boolean isNotContainsArquivosFileDiretorio(File file) {
        return (isFileInValido(file) || Objects.isNull(file.listFiles()) || (Objects.nonNull(file.listFiles()) && Objects.requireNonNull(file.listFiles()).length == 0));
    }

    public static boolean isFileInValido(File file) {
        return (Objects.equals(false, isFileValido(file)));
    }

    public static boolean isFileValido(File file) {
        return (Objects.nonNull(file) && file.exists() && file.canRead());
    }

    public static List<File> buscarListaFiles(File filePath, String extensaoArquivoFiltro) {
        return (List<File>) ((isNotContainsExtensaoComoFiltroBusca(extensaoArquivoFiltro))
                ? FileUtils.listFiles(filePath, FileFilterUtils.fileFileFilter(), null)
                : FileUtils.listFiles(filePath, FileFilterUtils.suffixFileFilter(extensaoArquivoFiltro), null));
    }

    private static boolean isNotContainsExtensaoComoFiltroBusca(String extensaoArquivoFiltro) {
        return (Objects.isNull(extensaoArquivoFiltro) || extensaoArquivoFiltro.isEmpty());
    }

    public static List<String> getRegistrosFromSeparadorLinhaArquivo(String linhaArquivo) {
        if (Objects.isNull(linhaArquivo) || linhaArquivo.isEmpty())
            throw new IllegalArgumentException("Conteudo da linha para separacao, encontra-se inválida (vazia) e/ou inexistente (null).");
        return getRegistrosFromLinhaArquivoPorSeparador(SEPARADOR_DEFAULT, linhaArquivo);
    }

    public static List<String> getRegistrosFromLinhaArquivoPorSeparador(String separador, String linhaArquivo) {
        return new ArrayList<>(Arrays.asList(linhaArquivo.split(separador)));
    }

    public static String escreveMap(Map<String, String> mapParameters, int index, String posicao, String value) {
        return (isParamsPermiteEscritaMapParameters(index, posicao)) ? mapParameters.put(posicao, value) : mapParameters.put("", "");
    }

    public static boolean isParamsPermiteEscritaMapParameters(int index, String posicao) {
        return (index >= 0 && Objects.nonNull(posicao) && String.valueOf(index).equals(posicao));
    }

    public static Map<String, String> getStringStringMap(List<String> linhasRegistro, String posicaoA, String posicaoB, String posicaoC) {
        Map<String, String> mapParameters = new HashMap<>();

        for (int i = 0; i < linhasRegistro.size(); i++) {
            escreveMap(mapParameters, i, posicaoA, linhasRegistro.get(i));
            escreveMap(mapParameters, i, posicaoB, linhasRegistro.get(i));
            escreveMap(mapParameters, i, posicaoC, linhasRegistro.get(i));
        }
        return mapParameters;
    }

    public static List<String> getLinhasRegistrosArquivos(String pathCaminhoArquivo) throws IOException {
        if (Objects.isNull(pathCaminhoArquivo) || pathCaminhoArquivo.isEmpty())
            throw new IllegalArgumentException("Conteudo da linha para separacao, encontra-se inválida (vazia) e/ou inexistente (null).");

        List<String> linhasArquivo;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathCaminhoArquivo))) {
            linhasArquivo = new ArrayList<>();

            String linha = "";
            while (linha != null) {
                linha = bufferedReader.readLine();
                if (isSeparadorInvalido(linha))
                    linhasArquivo.add(linha);
            }
        }
        return linhasArquivo;
    }

    public static File criarPathDiretorioInexistente(File filePathDiretorio) {
        if (!filePathDiretorio.exists()) {
            filePathDiretorio.mkdir();
        }
        return filePathDiretorio;
    }

    public static void gravarArquivo(String conteudoArquivo, File arquivoNovo) throws IOException {
        try (FileWriter fileWriter = new FileWriter(arquivoNovo.getAbsolutePath())) {
            fileWriter.write(conteudoArquivo);
            fileWriter.flush();
        } catch (IOException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    private static boolean isSeparadorInvalido(String linha) {
        return (Objects.nonNull(linha) && !linha.isEmpty() && linha.contains(SEPARADOR_DEFAULT));
    }
}
