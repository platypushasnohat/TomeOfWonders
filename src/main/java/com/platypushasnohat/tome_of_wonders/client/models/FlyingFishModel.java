package com.platypushasnohat.tome_of_wonders.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.platypushasnohat.tome_of_wonders.client.animations.FlyingFishAnimations;
import com.platypushasnohat.tome_of_wonders.entities.FlyingFish;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("FieldCanBeLocal, unused")
public class FlyingFishModel extends HierarchicalModel<FlyingFish> {

	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart dorsalfin;
	private final ModelPart leftpelvicfin;
	private final ModelPart rightpelvicfin;
	private final ModelPart tailfin;
	private final ModelPart backwingright;
	private final ModelPart backwingleft;
	private final ModelPart leftwing;
	private final ModelPart rightwing;

	public FlyingFishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.tail = this.body.getChild("tail");
		this.dorsalfin = this.tail.getChild("dorsalfin");
		this.leftpelvicfin = this.tail.getChild("leftpelvicfin");
		this.rightpelvicfin = this.tail.getChild("rightpelvicfin");
		this.tailfin = this.tail.getChild("tailfin");
		this.backwingright = this.tail.getChild("backwingright");
		this.backwingleft = this.tail.getChild("backwingleft");
		this.leftwing = this.body.getChild("leftwing");
		this.rightwing = this.body.getChild("rightwing");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -3.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create()
				.texOffs(0, 9).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition dorsalfin = tail.addOrReplaceChild("dorsalfin", CubeListBuilder.create()
				.texOffs(12, 18).addBox(0.0F, -1.0F, -1.5F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offset(0.0F, -1.0F, 2.5F));

		PartDefinition leftpelvicfin = tail.addOrReplaceChild("leftpelvicfin", CubeListBuilder.create()
				.texOffs(18, 12).addBox(0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.005F)), PartPose.offset(0.0F, 1.0F, 4.0F));

		PartDefinition rightpelvicfin = tail.addOrReplaceChild("rightpelvicfin", CubeListBuilder.create()
				.texOffs(18, 12).addBox(0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 4.0F));

		PartDefinition tailfin = tail.addOrReplaceChild("tailfin", CubeListBuilder.create()
				.texOffs(18, 0).addBox(0.0F, -2.0F, -1.0F, 0.0F, 7.0F, 5.0F, new CubeDeformation(0.005F)), PartPose.offset(0.0F, 0.0F, 7.0F));

		PartDefinition backwingright = tail.addOrReplaceChild("backwingright", CubeListBuilder.create()
				.texOffs(8, 18).addBox(0.0F, 0.0F, 0.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.005F)), PartPose.offset(-1.0F, 1.0F, 2.0F));

		PartDefinition backwingleft = tail.addOrReplaceChild("backwingleft", CubeListBuilder.create()
				.texOffs(8, 18).addBox(0.0F, 0.0F, 0.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.005F)), PartPose.offset(1.0F, 1.0F, 2.0F));

		PartDefinition leftwing = body.addOrReplaceChild("leftwing", CubeListBuilder.create()
				.texOffs(0, 18).addBox(0.0F, -10.0F, 0.0F, 0.0F, 10.0F, 4.0F, new CubeDeformation(0.005F)), PartPose.offset(1.0F, -1.0F, -1.0F));

		PartDefinition rightwing = body.addOrReplaceChild("rightwing", CubeListBuilder.create()
				.texOffs(0, 18).addBox(0.0F, -10.0F, 0.0F, 0.0F, 10.0F, 4.0F, new CubeDeformation(0.005F)), PartPose.offset(-1.0F, -1.0F, -1.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(FlyingFish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animate(entity.flopAnimationState, FlyingFishAnimations.FLOP, ageInTicks);
		this.animate(entity.glidingAnimationState, FlyingFishAnimations.SKIM, ageInTicks);
		this.animate(entity.swimmingAnimationState, FlyingFishAnimations.SWIM, ageInTicks, 0.5F + limbSwingAmount * 1.5F);

		float prevOnLandProgress = entity.prevOnLandProgress;
		float onLandProgress = entity.onLandProgress;
		float partialTicks = ageInTicks - entity.tickCount;
		float landProgress = prevOnLandProgress + (onLandProgress - prevOnLandProgress) * partialTicks;

		if (!entity.isGliding()) {
			this.root.xRot = headPitch * (Mth.DEG_TO_RAD);
		}
		this.root.zRot += landProgress * ((float) Math.toRadians(-90) / 5F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}