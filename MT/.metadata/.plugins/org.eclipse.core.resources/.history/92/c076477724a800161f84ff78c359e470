package com.pch.joomla.configuration;

import com.pch.utilities.Action_Wrapper;

public class JoomlaAdministrator extends Action_Wrapper {

	public ManageOauthClient manageOauthClient = new ManageOauthClient();
	public AdminLogin login = new AdminLogin();
	public UserManager user = new UserManager();
	public ArticleManager article = new ArticleManager();
	public MidTierApiConfiguration MTAPIConfig = new MidTierApiConfiguration();
	public Menu_Links ML = getMenuInstance();
	public ArticleActions actions = getActionsInstance();
	public PchApp PA = getPchAppInstance();
	public AliasPage alias = getAliasInstance();
	public Menu_Links ContentPath = getMenuInstance();	
    public ArticleContinuousGame CG = getContinuousGameInstance();
	
	public ArticleContinuousGame getContinuousGameInstance() {
		if (CG == null)
			CG = new ArticleContinuousGame();
		return CG;
	}
	public Menu_Links getMenuInstance() {
		if (ML == null)
			ML = new Menu_Links();
		return ML;
	}

	public ArticleActions getActionsInstance() {
		if (actions == null)
			actions = new ArticleActions();
		return actions;
	}

	public PchApp getPchAppInstance() {
		if (PA == null)
			PA = new PchApp();
		return PA;
	}

	public AliasPage getAliasInstance() {
		if (alias == null)
			alias = new AliasPage();
		return alias;
	}
}