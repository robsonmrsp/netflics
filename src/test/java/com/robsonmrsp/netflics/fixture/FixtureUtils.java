package com.robsonmrsp.netflics.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

import com.robsonmrsp.netflics.model.Genre;
import com.robsonmrsp.netflics.model.Movie;
import com.robsonmrsp.netflics.model.Language;
import com.robsonmrsp.netflics.model.Actor;
import com.robsonmrsp.netflics.model.User;
import com.robsonmrsp.netflics.model.Role;
import com.robsonmrsp.netflics.model.Permission;
import com.robsonmrsp.netflics.model.Group;
import com.robsonmrsp.netflics.model.Item;
public class FixtureUtils {

	public static void init() {
		Fixture.of(Genre.class).addTemplate("novo", new Rule() {
			{
				add("name", regex("[a-z ]{5,15}"));
			}
		});
		Fixture.of(Movie.class).addTemplate("novo", new Rule() {
			{
				add("sinopsis", regex("[a-z ]{5,15}"));
				add("title", regex("[a-z ]{5,15}"));
			}
		});
		Fixture.of(Language.class).addTemplate("novo", new Rule() {
			{
				add("name", regex("[a-z ]{5,15}"));
			}
		});
		Fixture.of(Actor.class).addTemplate("novo", new Rule() {
			{
				add("name", regex("[a-z ]{5,15}"));
			}
		});
		Fixture.of(User.class).addTemplate("novo", new Rule() {
			{
				add("name", regex("[a-z ]{5,15}"));
				add("username", regex("[a-z ]{5,15}"));
				add("email", regex("[a-z ]{5,15}"));
				add("password", regex("[a-z ]{5,15}"));
				add("image", regex("[a-z ]{5,15}"));
			}
		});
		Fixture.of(Role.class).addTemplate("novo", new Rule() {
			{
				add("authority", regex("[a-z ]{5,15}"));
				add("description", regex("[a-z ]{5,15}"));
			}
		});
		Fixture.of(Permission.class).addTemplate("novo", new Rule() {
			{
				add("name", regex("[a-z ]{5,15}"));
				add("description", regex("[a-z ]{5,15}"));
				add("operation", regex("[a-z ]{5,15}"));
				add("tagReminder", regex("[a-z ]{5,15}"));
			}
		});
		Fixture.of(Group.class).addTemplate("novo", new Rule() {
			{
				add("name", regex("[a-z ]{5,15}"));
				add("description", regex("[a-z ]{5,15}"));
			}
		});
		Fixture.of(Item.class).addTemplate("novo", new Rule() {
			{
				add("name", regex("[a-z ]{5,15}"));
				add("itemType", regex("[a-z ]{5,15}"));
				add("identifier", regex("[a-z ]{5,15}"));
				add("description", regex("[a-z ]{5,15}"));
			}
		});

	}
}