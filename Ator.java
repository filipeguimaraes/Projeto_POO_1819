import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe base Ator
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçanlo Ferreira A84073
 */
public abstract class Ator implements Serializable {
    private String nome;
    private int nif;
    private String email;
    private String password;
    private String morada;
    private LocalDateTime data;
    private Classificacao classificacao;
    private List<Aluguer> historico;

    public Ator(){
        this.email = "";
        this.nif = 0;
        this.nome = "";
        this.password = "";
        this.morada = "";
        this.data = LocalDateTime.now();
        this.classificacao= new Classificacao();
        this.historico = new ArrayList<>();
    }

    public Ator(String email,int nif, String nome, String password, String morada, LocalDateTime data,
                Classificacao classificacao,List<Aluguer> historico){
        this.email = email;
        this.nome = nome;
        this.nif=nif;
        this.password = password;
        this.morada = morada;
        this.data = data;
        this.classificacao=classificacao;
        this.historico = historico;
    }

    public Ator(Ator c){
        this.email = c.getEmail();
        this.nome = c.getNome();
        this.nif = c.getNif();
        this.password = c.getPassword();
        this.morada = c.getMorada();
        this.data = c.getData();
        this.classificacao = c.getClassificacao();
        this.historico = c.getHistorico();
    }

    public String getEmail(){
        return this.email;
    }

    public String getNome(){
        return this.nome;
    }

    public String getPassword(){
        return this.password;
    }

    public String getMorada(){
        return this.morada;
    }

    public LocalDateTime getData(){
        return this.data;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setMorada(String morada){
        this.morada = morada;
    }

    public void setData(LocalDateTime data){
        this.data = data;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    public void adicionaClassificacao(int classificacao){
        this.classificacao.adicionaClassificacao(classificacao);
    }

    public List<Aluguer> getHistorico() {
        return this.historico.stream().map(Aluguer::clone).collect(Collectors.toList());
    }

    public void setHistorico(List<Aluguer> historico) {
        this.historico = new ArrayList<>(historico.size());
        for (Aluguer a : historico)
            this.historico.add(a.clone());
    }

    /**
     * Adiciona aluguer à lista de Alugueres
     * @param aluguer Aluguer
     */
    public void adicionaAluguer(Aluguer aluguer){
        this.historico.add(aluguer);
    }

    /**
     * Determinar a classificação média do ator
     * @return Classificação média
     */
    public double classificacaoMedia(){
        return this.classificacao.classificacaoMedia();
    }

    /**
     * Determina a lista de alugueres que já foram aceitados
     * @return Lista de alugueres que foram aceites
     * @throws AluguerException Caso não haja alugueres aceites
     */
    public List<Aluguer> listaAlugueresAceites() throws AluguerException{

        List<Aluguer> ls = this.getHistorico().stream()
                .filter(l->l.getEstado()==Aluguer.ACEITE)
                .map(Aluguer::clone)
                .collect(Collectors.toList());

        if(ls.isEmpty()) throw new AluguerException("Não há alugueres aceites!");
        else return ls;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ator ator = (Ator) o;
        return nif == ator.nif &&
                nome.equals(ator.nome) &&
                email.equals(ator.email) &&
                password.equals(ator.password) &&
                morada.equals(ator.morada) &&
                data.equals(ator.data) &&
                classificacao.equals(ator.classificacao);
    }

    public abstract Ator clone();

    public String toString(){
        StringBuilder s= new StringBuilder();
        s.append("Geral{");
        s.append(" Email: " + this.email);
        s.append(", Nome: " + this.nome);
        s.append(", Password: " + this.password);
        s.append(", Morada: " + this.morada);
        s.append(", Data: " + this.data.toString());
        s.append(", Classificação: " + this.classificacao.toString() +'}');
        s.append(", Historico: " + this.historico.toString() +'}');
        return s.toString();
    }
}

