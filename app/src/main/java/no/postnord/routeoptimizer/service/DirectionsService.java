package no.postnord.routeoptimizer.service;

import no.postnord.routeoptimizer.model.DirectionResponse;
import retrofit.http.GET;
import retrofit.http.Query;

public interface DirectionsService {
    @GET("/json")
	DirectionResponse getDirections(@Query("origin") String origin, @Query("destination") String destination,
									@Query("waypoints") String waypoints, @Query("language") String language);
}
