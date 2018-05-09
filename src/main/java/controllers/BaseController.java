package controllers;

import models.IDatabaseAccess;
import models.MockDb;

public class BaseController {
	public IDatabaseAccess dbAccess;
	public BaseController() {
		dbAccess = new MockDb();
	}
}
