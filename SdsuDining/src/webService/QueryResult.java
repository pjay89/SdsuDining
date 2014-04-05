package webService;

public abstract class QueryResult {
	private String tickerSymbol;
    private Double quote;
 
    public QueryResult() {
    	
    }
 
    public QueryResult(String tickerSymbol, Double quote) {
        this.tickerSymbol = tickerSymbol;
        this.quote = quote;
    }

 
    public String getTickerSymbol() {
        return tickerSymbol;
    }
    
 
    public Double getQuote() {
        return quote;
    }
    
/*    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }
    public void setQuote(double quote) {
        this.quote = quote;
    }*/
}
