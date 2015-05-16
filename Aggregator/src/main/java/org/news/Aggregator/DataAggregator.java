@Component
@Provides
@Instanciate

public class DataAggregator implements SearchInfoItf{

    @Requires(filters type = "SI.FINDER"))
    List<SearchInfoItf > dataFinders;

    @ServiceProperty(value="agregateur")
    String type;

    public Map<URL,String> searchinfo(String query){
        Map<URL,String> resultMap = new HashMap<URL,String>(), tmpMap = null;
        Iterator<Map.Entry<URL,String>> itTmp = null;
        Map.Entry<URL,String> tmpEntry = null;

        for(int i = 0; i<dataFinders.size(); i++)
        {
            tmpMap = dataFinders.get(i).search(query);
            itTmp = tmpMap.entrySet().iterator();

            while(itTmp.hasNext())
            {
                tmpEntry = itTmp.next();
                if(!resultMap.contains(tmpEntry))
                {
                    resultMap.add(tmpEntry);
                }
            }
        }

        return resultMap;
    }
}
