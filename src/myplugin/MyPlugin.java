package myplugin;

import java.io.File;

import javax.swing.JOptionPane;

import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.ProjectOptions;


import com.nomagic.actions.NMAction;
import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;

/** MagicDraw plugin that performes code generation */
public class MyPlugin extends com.nomagic.magicdraw.plugins.Plugin {

	String pluginDir = null;

	public void init() {
		pluginDir = getDescriptor().getPluginDirectory().getPath();

		// Creating submenu in the MagicDraw main menu
		ActionsConfiguratorsManager manager = ActionsConfiguratorsManager.getInstance();
		manager.addMainMenuConfigurator(new MainMenuConfigurator(getSubmenuActions()));

		/** @Todo: load project options (@see myplugin.generator.options.ProjectOptions) from
		 * ProjectOptions.xml and take ejb generator options */

		modelOptions();
		repositoryOptions();
		serviceOptions();
		controllerOptions();
//		jspOptions();
//		homeJspOptions();
//		detailJspOptions();
		listFtlOptions();
		indexFtlOptions();
		detailFtlOptions();
		editFtlOptions();
		createFtlOptions();
	}

	private void modelOptions() {
		GeneratorOptions generatorOptions = new GeneratorOptions("c:/Temp/mbrs/mbrs/src/main/java", "jpa_model", "templates", "{0}.java", true, "uns.ftn.mbrs.model");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("ModelLayerGenerator", generatorOptions);
		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
	}

	private void repositoryOptions() {
		GeneratorOptions generatorOptions = new GeneratorOptions("c:/Temp/mbrs/mbrs/src/main/java", "repositoryclass", "templates", "{0}Repository.java", true, "uns.ftn.mbrs.repository");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("RepositoryGenerator", generatorOptions);
		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
	}

	private void serviceOptions() {
		GeneratorOptions generatorOptions = new GeneratorOptions("c:/Temp/mbrs/mbrs/src/main/java", "service", "templates", "{0}GenService.java", true, "uns.ftn.mbrs.service");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("ServiceLayerGenerator", generatorOptions);
		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
	}

	private void controllerOptions() {
		GeneratorOptions generatorOptions = new GeneratorOptions("c:/Temp/mbrs/mbrs/src/main/java", "controller", "templates", "{0}GenController.java", true, "uns.ftn.mbrs.controller");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("ControllerLayerGenerator", generatorOptions);
		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
	}

//	private void jspOptions() {
//		GeneratorOptions generatorOptions = new GeneratorOptions("c:/Temp/mbrs/mbrs/src/main/webapp", "list_jsp", "templates", "{0}s.jsp", true, "webapp");
//		ProjectOptions.getProjectOptions().getGeneratorOptions().put("JspGenerator", generatorOptions);
//		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
//	}
//
//	private void homeJspOptions() {
//		GeneratorOptions generatorOptions = new GeneratorOptions("c:/Temp/mbrs/mbrs/src/main/webapp", "home", "templates", "{0}.jsp", true, "webapp");
//		ProjectOptions.getProjectOptions().getGeneratorOptions().put("HomeJspGenerator", generatorOptions);
//		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
//	}
//
//	private void detailJspOptions() {
//		GeneratorOptions generatorOptions = new GeneratorOptions("c:/Temp/mbrs/mbrs/src/main/webapp", "details_jsp", "templates", "{0}.jsp", true, "web");
//		ProjectOptions.getProjectOptions().getGeneratorOptions().put("DetailJspGenerator", generatorOptions);
//		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
//	}

	private void listFtlOptions() {
		GeneratorOptions generatorOptions = new GeneratorOptions("c:/Temp/mbrs/mbrs/src/main/resources", "list_page", "templates", "{0}s.ftlh", true, "webapp");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("ListFreemarkerGenerator", generatorOptions);
		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
	}
	private void indexFtlOptions() {
		GeneratorOptions generatorOptions = new GeneratorOptions("c:/Temp/mbrs/mbrs/src/main/resources", "index_page", "templates", "{0}.ftlh", true, "webapp");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("IndexFreemarkerGenerator", generatorOptions);
		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
	}
	private void detailFtlOptions() {
		GeneratorOptions generatorOptions = new GeneratorOptions("c:/Temp/mbrs/mbrs/src/main/resources", "details_page", "templates", "{0}.ftlh", true, "web");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("DetailFreemarkerGenerator", generatorOptions);
		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
	}
	private void editFtlOptions() {
		GeneratorOptions generatorOptions = new GeneratorOptions("c:/Temp/mbrs/mbrs/src/main/resources", "edit_page", "templates", "Edit{0}.ftlh", true, "web");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("EditFreemarkerGenerator", generatorOptions);
		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
	}
	private void createFtlOptions() {
		GeneratorOptions generatorOptions = new GeneratorOptions("c:/Temp/mbrs/mbrs/src/main/resources", "add_page", "templates", "Create{0}.ftlh", true, "web");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("CreateFreemarkerGenerator", generatorOptions);
		generatorOptions.setTemplateDir(pluginDir + File.separator + generatorOptions.getTemplateDir());
	}
	private NMAction[] getSubmenuActions()
	{
		return new NMAction[]{
				new GenerateAction("Generate"),
		};
	}

	public boolean close() {
		return true;
	}

	public boolean isSupported() {
		return true;
	}
}
