/*  generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56 */
package com.robsonmrsp.netflics.rs;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import com.robsonmrsp.netflics.core.json.JsonError;
import com.robsonmrsp.netflics.core.json.JsonPaginator;
import com.robsonmrsp.netflics.json.JsonActor;

import com.robsonmrsp.netflics.model.Actor;

import com.robsonmrsp.netflics.service.ActorService;
import com.robsonmrsp.netflics.model.filter.FilterActor;
import com.robsonmrsp.netflics.core.persistence.pagination.Pager;
import com.robsonmrsp.netflics.core.persistence.pagination.SearchParameters;
import com.robsonmrsp.netflics.core.rs.exception.ValidationException;
import com.robsonmrsp.netflics.core.security.SpringSecurityUserContext;

import com.robsonmrsp.netflics.utils.Parser;
@RestController
@RequestMapping("/rs/crud/actors")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ActorController {
	@Autowired
	ActorService actorService;
	@Autowired
	private SpringSecurityUserContext context;
	public static final Logger LOGGER = LoggerFactory.getLogger(ActorController.class);

	@RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity get(@RequestParam MultiValueMap<String, String> mapParams) {
		ResponseEntity response = null;

		Pager<Actor> actors = null;
		try {
			SearchParameters<FilterActor> paginationParams = new SearchParameters<FilterActor>(mapParams, FilterActor.class);

			actors = actorService.get(paginationParams, context.getTenant());
			
			JsonPaginator<JsonActor> paginator = JsonPaginator.of(Parser.toListJsonActors(actors.getItems()),
				 actors.getActualPage(), actors.getPageSize(), actors.getOrder(), actors.getOrderBy(),actors.getTotalRecords());

			response = ResponseEntity.ok(paginator);

		} catch (Exception e) {
			String message = String.format("Não foi possivel carregar actors para os parametros %s [%s]", mapParams.toString(), e.getMessage());
			LOGGER.error(message, e);
			response = ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new JsonError(e, message, null));
		}
		return response;
	}
	
	@RequestMapping(value = "{id:\\d+}", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity get(@PathVariable("id") int id) {
		try {
			Optional<Actor> optional = actorService.get(id, context.getTenant());
			
			if (optional.isPresent()) {
				return ResponseEntity.ok(Parser.toJson(optional.get()));
			}

			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			String message = String.format("Não foi possivel carregar o registro. [ %s ] parametros [ %d ]", e.getMessage(), id);
			LOGGER.error(message, e);
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new JsonError(e, message, null));
		}
	}

	@RequestMapping(method = POST, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity save(@RequestBody JsonActor jsonActor) {
		try {
			Actor actor = Parser.toEntity(jsonActor);

			actor = actorService.save(actor);

			return ResponseEntity.status(CREATED).body(Parser.toJson(actor));

		} catch (ValidationException e) {
			String message = String.format("Não foi possivel salvar  o registro [ %s ] parametros [ %s ]", e.getOrigem().getMessage(), jsonActor.toString());
			LOGGER.error(message, e.getOrigem());
			return ResponseEntity.status(BAD_REQUEST).body(new JsonError(e, message, jsonActor, e.getLegalMessage()));
		} catch (Exception e) {
			String message = String.format("Não foi possivel salvar  actor [ %s ] parametros [ %s ]", e.getMessage(), jsonActor.toString());
			LOGGER.error(message, e);
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new JsonError(e, message, jsonActor));

		}
	}

	@RequestMapping(value = "{id:\\d+}", method = PUT, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody JsonActor jsonActor) {
		try {
			Actor actor = Parser.toEntity(jsonActor);

			actor = actorService.update(actor);

			return ResponseEntity.ok(Parser.toJson(actor));
		} catch (ValidationException e) {
			String message = String.format("Não foi possivel salvar  o registro [ %s ] parametros [ %s ]", e.getOrigem().getMessage(), jsonActor.toString());
			LOGGER.error(message, e.getOrigem());
			return ResponseEntity.status(BAD_REQUEST).body(new JsonError(e, message, jsonActor, e.getLegalMessage()));
		} catch (Exception e) {
			String message = String.format("Não foi possivel salvar o registro [ %s ] parametros [ %s ]", e.getMessage(), jsonActor.toString());
			LOGGER.error(message, e);
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new JsonError(e, message, jsonActor));
		}
	}
	
	@RequestMapping(value = "{id:\\d+}", method = DELETE)
	public ResponseEntity delete(@PathVariable("id") Integer id) {
		try {
			actorService.delete(id, context.getTenant());
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			String message = String.format("Não foi possivel remover o registro [ %s ] parametros [ %s ]", e.getMessage(), id);
			LOGGER.error(message, e);
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new JsonError(e, message, id));
		}
	}

}
//generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56
