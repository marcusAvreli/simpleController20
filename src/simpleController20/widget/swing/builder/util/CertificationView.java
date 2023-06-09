package simpleController20.widget.swing.builder.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import simpleController20.core.annotation.Controller;
import simpleController20.core.annotation.Controllers;
import simpleController20.core.view.DefaultViewContainer;
import simpleController20.swing.swing.builder.SwingBuilder;
import simpleController20.swing.swing.builder.layout.GridBagConstraintsBuilder;
@Controllers({
	@Controller(type=CertificationController.class,pattern="getReportBtn"),
	
})
public class CertificationView extends DefaultViewContainer{
	public CertificationView(){
		super("CertificationView",
			new SwingBuilder().layout(new BorderLayout()).component(
					new SwingBuilder().
					layout(new GridBagLayout()).
					 /* (1) label-component */
						label(new GridBagConstraintsBuilder().row(0).col(0).gridWidth(2).build()).
							setName("certificationLbl").setText("Certification").swingBuilder()
							.button(new GridBagConstraintsBuilder().
									row(6).col(0).gridWidth(3).anchor(GridBagConstraints.EAST).fill(GridBagConstraints.NONE).insets(20,0,0,0).build()).
								setName("getReportBtn").setText("Get Report").setPreferredSize(new Dimension(180,25)).
						 /* Returning the container to add it to the frame */
					swingBuilder().getTarget()
							,JPanel.class,BorderLayout.NORTH).
						swingBuilder().setPreferredSize(new Dimension(400,0)).getTarget());
	}
}
