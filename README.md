# ITM_Mobile Programming (코틀린 프로젝트)
1달동안 코틀린을 언어로 안드로이드 어플리케이션을 개발하는 프로젝트입니다.<br/>
저희 팀은 식자재를 직구 및 공구할 수 있고 인벤토리를 관리할 수 있는 어플리케이션을 개발하였습니다.

## Project-Team7 

## 1. 배경 및 주제 선정
+ 비용 상승으로 이어지는 긴 유통과정 때문에 신선도 저하로 이어질 수 있다는 단점이 있습니다.<br/> 
+ 또한, 농장에서 직접 구매하려면 종종 대량으로 구매해야 하므로 개별 가구에 부담이 될 수 있습니다
+ 따라서, 농장의 공동 구매를 용이하게 하는 앱으로, 이웃들이 대량 주문을 공유하고 비용을 절감하며 신선도와 저렴한 가격을 보장하고자 합니다. 


## 2. 주요 기능

### 1) 로그인
+ 로그인: 설명쓰기
<img width="138" alt="로그인1" src="https://github.com/gumchinjun/VeggieNeighbor/assets/97167373/5d73974f-6502-4767-aa40-6a332799db84">
<img width="138" alt="로그인2" src="https://github.com/gumchinjun/VeggieNeighbor/assets/97167373/c8312704-e39f-4899-bbca-158abb5294aa">


+ __Routing Plan Development__: The second goal is to develop an efficient routing plan between these identified stations. This plan aims to outline the most effective paths that minimize travel distance and time for passengers, enhancing the overall efficiency of the air taxi service.   

### 2) Expected outcomes
+ __Optimal Station Locations__: This project will provide a comprehensive list of optimal locations for air taxi stations that are in harmony with the city's social and structural characteristics. Carefully selected based on important social factors such as population density and business concentration, these locations will offer extensive coverage and easy accessibility throughout the city, while minimizing the impact on the urban environment and complying with local regulations. This is expected to significantly enhance the efficiency and accessibility of the air taxi service.   

+ __Efficient Travel Routes__: A detailed routing plan that connects these stations. This plan should facilitate quick, direct, and efficient travel for passengers, significantly reducing the travel time compared to current transportation options.      

### 3) Constraints   
Data sources: The data was obtained only through public datasets and open APIs.   
Time: The scope of the data has been limited to the Seoul area so that the project can be processed for a month.   


## 4. Main Datasets
+ *OD dataset*   
[국가교통DB - 수도권 주수단 OD 데이터(서울 통행량 데이터 활용)](https://www.ktdb.go.kr/www/index.do)   

+ *Population Density & Number of Company*   
[서울열린데이터광장 - 서울시 인구밀도 (동별) 통계](https://data.seoul.go.kr/dataList/10584/S/2/datasetView.do)   
[서울시 사업체현황 통계](https://data.seoul.go.kr/)   

+ *Seoul subway & bus*   
[산림빅테이터거래소 - 지하철 역 정보(위치)](https://www.bigdata-forest.kr/product/PTP002901)   
[서울시열린데이터광장 - 서울시 버스정류소 위치정보](https://data.seoul.go.kr/dataList/OA-15067/S/1/datasetView.do)   
[서울시열린데이터광장 - 서울시 버스노선별 정류장별 승하차 인원 정보](https://data.seoul.go.kr/dataList/OA-12912/S/1/datasetView.do)   

+ *Seoul geographic data*   
[국가공간정보포털 - (도로명주소)도로구간](http://data.nsdi.go.kr/dataset/12902)   
[국가공간정보포털 - 하천경계](http://data.nsdi.go.kr/dataset/20180927ds0054)   

+ *Google Map API and Kakao Map API*   


## 5. Preprocessing

### 1) EDA   
- Metadata provided by the data source allows you to grasp the context of the data.
- Statistics and simple plots (distribution, scatter, and box plot) were used to analyze the data.   
- From the OD data, it could be identified by the distribution plot that traffic in only a few regions during commuting time is significantly higher than that in most other regions.   

### 2) Feature Selection and Extraction   
- The information required from the original dataset was selected and combined.   
- A new table was created combining the morning and afternoon peak time tables of OD data, and a new column was created combining the traffic volume columns of several ways of transportation.   
- For visualization, the administrative district(dong) and coordinate information were matched with the API of the map services.   

### 3) Visualization   
- Since it deals with spatial data, visualization was taken into consideration to easily grasp information. Visualization was required in all other processes, not only in the preprocessing.   
- Python's Folium, Pydeck, etc libraries was used.   
- Arc visualization of OD data - Top 1000 traffic by buses at AM peak time in Seoul(origin: red / destination: green)![image](https://github.com/phrabit/ITM_Business-Analytics/assets/52899088/5f112467-1418-47fe-b7f7-50c7a89d1cd3)
- Visualization with dark colors on Dongs having the high-level values of the features - Top 10 Dongs with income level, population density, and number of companies in Seoul![image](https://github.com/phrabit/ITM_Business-Analytics/assets/52899088/b20e6e5d-99f8-4313-a5ed-eb47fb4ea483)



## 6. Model

### 1) K-means Clustering

   K-means Clustering is a distance-based clustering algorithm for dividing data into K clusters.
K-means Clustering assumes that data in the same cluster have similar features and data in different clusters have opposite features. In other words, it not only considers clustering within the same cluster, but also considers the relationship with other clusters. 
K-means clustering is simple, fast, and performs well.

> It is sensitive to outliers.
K-means Clustering is sensitive to outliers. In particular, if an outlier is selected as the centroid, it can lead to strange clustering results, so apply the model after preprocessing using <b>StandardScaler</b>.

   ![image](https://github.com/phrabit/ITM_Business-Analytics/assets/70180003/f63a883c-cce4-422a-8272-0f8c4582b997)



## 7. Project Flow

### 1) Feedback Reflected Clustering
   + In progress, using three features(Income level, # of companies, population density), we did clustering.
     However, it is meaningless since just ranking those features might be more efficient. Thus, we found knee points for # of companies, population density and transportation_total. Sum all of those top instances, then we proceed to do k-means clustering

     ![image](https://github.com/phrabit/ITM_Business-Analytics/assets/70180003/163a2dd4-be5d-451f-98a0-7c8cc25d55ca)


### 2) Select a specific 'Dong' for each Cluster

When selecting a specific block within a cluster, the accessibility of each block was considered. The accessibility index was calculated by summing the distance-based supply amounts for each cluster using the Hansen estimation method. The selected blocks from the 6 clusters are as follows:

1. Gasan-dong, Geumcheon-gu
2. Gileum 1(il)-dong, Seongbuk-gu
3. Gil-dong, Gangdong-gu
4. Jongno 1(il).2(i).3(sam).4(sa)-ga-dong, Jongno-gu
5. Gayang 1(il)-dong, Gangseo-gu
6. Yeoksam 1(il)-dong, Gangnam-gu

![image](https://github.com/phrabit/ITM_Business-Analytics/assets/119919849/7f299f06-a9c4-44f5-9aac-4cd7377d6d6f)

### 3) Evaluate Transportation Access for Each Candidate ‘Dong'
   Public transportation stops within the representative candidate "dong" selected by clusters are set as candidate sites for Air taxi location, and then the priority of Air taxi location of the candidate sites is finally derived by considering the connection with other transportation methods.

1. Integration of Seoul public transportation (bus, subway) stop location information
- Collect data on bus stops in Seoul
- Collect subway station data in Seoul
- Merge bus stop and subway station data
- Finally, use the respective latitude and longitude coordinate data and administrative building information of the area (using Google Map API, Kakao API)

2. Calculate the number of other stops within 300 meters of each public transportation stop
- Why 300m?
Because the plane distance cannot exceed 300m when setting up a transfer center. Therefore, the maximum possible transfer distance between public transportation and air taxi is set to 300m. (According to the Road Traffic Administration Rules)

3. Prioritize Air taxi stops for representative candidate  "dong" in each cluster
- Data of 6 candidate "dong", one per cluster
- Data on the number of other stops within 300 meters of each public transportation stop
- Merge the two datasets.

> Candidate "dong" selection criteria
> 1) The number of subway stations within a 300m radius of each stop location is prioritized.
> 2) If the number of subway stations is the same, the number of bus stops within a 300m radius is the final prioritization.

4. Visualize the results
![image](https://github.com/phrabit/ITM_Business-Analytics/assets/70180003/e6a7bcef-ea14-421b-95a0-65ccd981c353)


### 4) Finding the optimal route among stations
1. Set the constraints(P-73, noise issue)
- No-fly Zone: P-73 (2023) - 3.704km radius of War Memorial of Korea in Yongsan, Seoul
- Altitude and Noise Issue: Road and river shp data in Seoul

2. Cost all paths according to each constraint.
- H3 (Hexagonal hierarchical geospatial indexing system) fills the entire area of Seoul.
- Hexagons intersecting wide roads and passing through rivers have high costs.

3. Find the path where cost between the two stations is minimized.(Dijkstra Algorithm)
> 1) Targets: 6 Hexagons containing the 6 selected station locations at the previous step
> 2) Nodes: All Hexagons
> 3) Edges: Each hexagon’s straight path to neighbor hexagons
> 4) Costs: costs in Hexagons
 
4. Visualize the results
![readmeroute](https://github.com/phrabit/ITM_Business-Analytics/assets/97167373/ce0f7ad1-76cf-440c-908f-fe218b8af637)



## 8. Conclusion
 +  This project has successfully pinpointed six prime locations for air taxi stations, strategically situated near major transit points to facilitate easy access and connectivity. By leveraging a hexagonal zoning approach in Seoul and employing Dijkstra's algorithm for economical pathfinding, we've optimized air taxi routes for maximum efficiency. This initiative is poised to significantly bolster the integration with existing public transportation networks, alleviate road traffic congestion, and play a pivotal role in establishing a robust air taxi system in Seoul, with far-reaching economic benefits.
